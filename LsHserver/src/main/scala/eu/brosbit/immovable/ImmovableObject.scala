package eu.brosbit.immovable

trait ImmovableObject {
  def nextDay():Unit
  def getHP():Short
  val obj:ImmovableObjectConst
  def log:String

}

trait  ImmovableObjectConst {
  val name:String
  val shortName:String
}