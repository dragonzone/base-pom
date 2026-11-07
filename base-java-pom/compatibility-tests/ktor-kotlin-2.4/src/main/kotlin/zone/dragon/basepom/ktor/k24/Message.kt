package zone.dragon.basepom.ktor.k24

import kotlinx.serialization.Serializable

/** A serializable payload exercising the kotlinx.serialization compiler plugin and runtime. */
@Serializable
data class Message(val text: String, val recipient: String)
