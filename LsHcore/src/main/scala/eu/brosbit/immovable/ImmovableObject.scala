package eu.brosbit.immovable

trait ImmovableObject {
  def nextDay():Unit
  def getHP():Short
  val name:String
  val shortName:String

  def log:String

}