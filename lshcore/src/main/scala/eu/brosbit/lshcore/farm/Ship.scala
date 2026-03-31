package eu.brosbit.lshcore.farm

class Ship extends FarmAnimal:
  override val Const = ShipConst

object ShipConst extends  FarmAnimalConst:
  val consumption = 3
  val dayProduce = 1
  val weekProduce = 1
  val meat: Int = 90 
  val leather: Int = 2
  val reproduceFactor = 0.2f
  val growWeeks = 6

