case class User(_id:String,  pass_hash:String, roles:String, scopes)
case class Client(_id:String, secret:String, redirect_url:String, scope:String, auth_grants:String)



