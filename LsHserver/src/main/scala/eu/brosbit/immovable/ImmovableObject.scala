package eu.brosbit.immovable

class ImmovableObject(pl:Plant) {
  var plant:Plant = pl
  var build:Boolean = false
  val building:Option[Building] = None
  def log:String = ""

}