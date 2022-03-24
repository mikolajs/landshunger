package eu.brosbit

import eu.brosbit.UserRegistry.ActionPerformed

//#json-formats
import spray.json.DefaultJsonProtocol

object JsonFormats:
  // import the default encoders for primitive types (Int, String, Lists etc)
  import DefaultJsonProtocol._
  import spray.json.JsonFormat

  given JsonFormat[User] = jsonFormat3(User.apply)
  given JsonFormat[Users] = jsonFormat1(Users.apply)

  given JsonFormat[ActionPerformed] = jsonFormat1(ActionPerformed.apply)

//#json-formats
