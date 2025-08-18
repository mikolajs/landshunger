package eu.brosbit.lshcore.immovable

class Wasteland extends ImmovableObject:
  val obj = Wasteland
  override def getHP: Int = 0
  override def nextDay(): Unit = {}
  override def log = "empty"
  override def toJson: String = "" 
  
object Wasteland extends ImmovableObjectConst:
  override val aType: String = "W" 
  override val name: String = "waste"
  override val shortName: String = "w"
  override val symbol: String = "#"
  override val maxHP: Int = 0
