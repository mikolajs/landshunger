package eu.brosbit.lshcore.farm

class Pigs extends FarmAnimal:
  override val Const = PigsConst

object PigsConst extends FarmAnimalConst:
  val consumption = 2
  val dayProduce = 0
  val weekProduce = 0
  val meat: Int = 30
  val leather: Int = 3
  val reproduceFactor = 0.3f
  val growWeeks = 5

