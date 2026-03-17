package eu.brosbit.lshcore.farm

class Cows extends FarmAnimal:
  override val Const = CowsConst

object CowsConst extends  FarmAnimalConst:
  val consumption = 5
  val dayProduce = 3
  val weekProduce = 1
  val meat: Int =  20
  val leather: Int = 3
  val reproduceFactor = 0.2f

