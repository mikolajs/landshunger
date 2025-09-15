package eu.brosbit.lshcore.immovable

trait ImmovableObject {
  def nextDay():Unit
  def getHP:Int
  val obj:ImmovableObjectConst
  def log:String
  def toJson:String

}

trait  ImmovableObjectConst {
  val aType:String
  val name:String
  val shortName:String
  val symbol:String
  val maxHP:Int
  def toJson:String =
    s"""{"t":"$aType","n":"$name","hpMax":$maxHP}"""
}
