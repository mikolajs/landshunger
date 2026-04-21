package eu.brosbit.lshcore.farm

class Cows extends FarmAnimal:
  override val Const = CowsConst

object CowsConst extends  FarmAnimalConst:
  val consumption = 6
  val dayProduce = 2
  val weekProduce = 1
  val meat: Int =  300
  val leather: Int = 5
  val reproduceFactor = 0.2f
  val growWeeks = 8
