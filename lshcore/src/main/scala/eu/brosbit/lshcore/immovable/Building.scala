
package eu.brosbit.lshcore.immovable

import eu.brosbit.lshcore.items.Item

abstract class Building(row:Int, col:Int) extends ImmovableObject:
  var items:List[Item] = Nil
  var itemsStorage:Int = 0
  protected var HP:Int = 0
  protected var hp = 1
  override def getHP: Int = hp
  override def log = "Brak"
  override def toJson = ""
  override def nextDay(): Unit = {}


trait BuildingConst extends ImmovableObjectConst:
  override val aType = "B"
  val name:String = "Building"
  val shortName = "bi"
  val symbol = "b"
  val maxHP:Int
  val maxHomes:Int //how many groups of people can live here
  val maxStorage:Int
  val buildEffort:Int
  val strongBuilding:Int //how heavy to destroy
  val image = ""
  val stoneNeed:Int
  val woodNeed:Int
  val ironNeed:Int
