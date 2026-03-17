package eu.brosbit.lshcore.farm

class Ship extends FarmAnimal:
  override val Const = ShipConst

object ShipConst extends  FarmAnimalConst:
  val consumption = 2
  val dayProduce = 1
  val weekProduce = 1
  val meat: Int = 5
  val leather: Int = 1
  val reproduceFactor = 0.2f

