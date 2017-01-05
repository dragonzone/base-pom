#!Jenkinsfile

// Project Config
def buildEnvironmentImage = "maven:3.3.9-jdk-8"
def allowedBranchRegex = ".*" // ( PRs are in the form 'PR-\d+' )
def defaultBranchRegex = "master"

// Maven Config
def mavenArgs = "-B -U -Dci=true -Dmaven.test.failure.ignore"
def mavenVaildateProjectGoals = "clean validate"
def mavenDefaultGoals = "-DpushChanges=false -DlocalCheckout=true -DpreparationGoals=initialize release:prepare release:perform"
def mavenNonDefaultGoals = "verify"

// Pipeline Definition
node("docker") {
    // Prepare the docker image to be used as a build environment
    def buildEnv = docker.image(buildEnvironmentImage)
    def isDefaultBranch = env.BRANCH_NAME.matches(defaultBranchRegex)
    stage("Prepare Build Environment") {
        buildEnv.pull()
    }

    buildEnv.inside {
        withMaven(localRepo: ".m2/repository", globalMavenSettingsConfig: "maven-dragonZone") {
            // Download source and dependencies
            stage("Checkout & Validate Project") {
                checkout scm
                sh "mvn ${mavenArgs} ${mavenVaildateProjectGoals}"
            }

            // Set Build Information
            def pom = readMavenPom(file: "pom.xml")
            def name = pom.artifactId
            def version = pom.version.replace("-SNAPSHOT", ".${env.BUILD_NUMBER}")
            currentBuild.displayName = "${name}-${version}"

            def gitSha1 = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
            currentBuild.description = "${env.BRANCH_NAME}@${gitSha1}"

            // Actually build the project
            stage("Build Project") {
                if (isDefaultBranch) {
                    sh "mvn ${mavenArgs} -DreleaseVersion=${version} -DdevelopmentVersion=${pom.version} ${mavenDefaultGoals}"
                    sh "git push ${pom.artifactId}-${version}"
                } else {
                    sh "mvn ${mavenArgs} ${mavenNonDefaultGoals}"
                }
            }
        }
    }
}
