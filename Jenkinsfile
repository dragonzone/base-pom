#!Jenkinsfile

// Project Config
def buildEnvironmentImage = "maven:3.3.9-jdk-8"
def buildableBranchRegex = ".*" // ( PRs are in the form 'PR-\d+' )
def deployableBranchRegex = "master"

// Maven Config
def mavenArgs = "-B -U -Dci=true -e -X"
def mavenValidateProjectGoals = "clean initialize"
def mavenNonDeployGoals = "verify"
def mavenDeployGoals = "deploy -DdeployAtEnd=true -DupdateReleaseInfo=true"

// Bail if we shouldn't be building
if (!env.BRANCH_NAME.matches(buildableBranchRegex)) {
    echo "Branch ${env.BRANCH_NAME} is not buildable, aborting."
    return
}

// Pipeline Definition
node("docker") {
    // Prepare the docker image to be used as a build environment
    def buildEnv = docker.image(buildEnvironmentImage)
    def isDeployableBranch = env.BRANCH_NAME.matches(deployableBranchRegex)

    stage("Prepare Build Environment") {
        buildEnv.pull()
    }

    buildEnv.inside {

        withMaven(localRepo: "${env.WORKSPACE}/.m2/repository", globalMavenSettingsConfig: "maven-dragonZone") {
            echo "${env.WORKSPACE}/.m2/repository"
            // Download source and dependencies
            stage("Checkout & Initialize Project") {
                checkout scm
                sh "git config user.name ${env.CHANGE_AUTHOR}"
                sh "git config user.email ${env.CHANGE_AUTHOR_EMAIL}"
                sh "git clean -f && git reset --hard origin/master"
                sh "mvn ${mavenArgs} ${mavenValidateProjectGoals}"
            }

            // Set Build Information
            def gitSha1 = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
            def pom = readMavenPom(file: "pom.xml")
            def name = pom.artifactId
            def version = pom.version.replace("-SNAPSHOT", ".${env.BUILD_NUMBER}")
            currentBuild.displayName = "${pom.artifactId}-${version}-${gitSha1.take(6)}"


            stage("Validate Project") {
                echo "Setting version to ${version}-${gitSha1.take(6)}"
                sh "mvn ${mavenArgs} release:prepare -Dresume=false -Darguments=\"${mavenArgs}\" -DpushChanges=false -DpreparationGoals=initialize -Dtag=${pom.artifactId}-${version + (isDeployableBranch ? '' : '-' + gitSha1.take(6))} -DreleaseVersion=${version}-${gitSha1.take(6)} -DdevelopmentVersion=${pom.version}"
            }

            // Actually build the project
            stage("Build Project") {
                try {
                    sh "mvn release:perform -DlocalCheckout=true -Dgoals=\"${isDeployableBranch ? mavenDeployGoals : mavenNonDeployGoals}\" -Darguments=\"${mavenArgs}\""
                    archiveArtifacts "**/target/*.jar"

                    if (isDeployableBranch) {
                        try {
                            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'github-baharclerode-user', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD']]) {
                                sh("git config credential.username ${env.GIT_USERNAME}")
                                sh("git config credential.helper '!echo password=\$GIT_PASSWORD; echo'")
                                sh("GIT_ASKPASS=true git push origin --tags")
                            }
                        } finally {
                            sh("git config --unset credential.username")
                            sh("git config --unset credential.helper")
                        }
                    }
                } finally {
                    //junit "**/target/surefire-reports/TEST-*.xml"
                }
            }
        }
    }
}
