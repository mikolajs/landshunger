package eu.brosbit.immovable

trait ImmovableObject {
  def nextDay():Unit
  def getHP():Short
  val obj:ImmovableObjectConst
  def log:String

  def jsonStr:String

}

trait  ImmovableObjectConst {
  val genType:String
  val name:String
  val shortName:String
  val maxHP:Short
}
