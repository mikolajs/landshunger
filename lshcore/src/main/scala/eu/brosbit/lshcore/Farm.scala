package eu.brosbit.lshcore

import eu.brosbit.lshcore.items.ItemList

abstract class FarmAnimal:
  val Const:FarmAnimalConst
  var n = 10
  var y = 0
  var weeks = 0
  var maxN = 120
  var lastFeeding = 0.0f
  var feeding = 1.0f

  def eat(food: Int): Int =
    val nrReturn = if Const.consumption * (n + y / 2) <= food then
      lastFeeding = 1.0f
      food - Const.consumption * (n + y / 2)
    else
      lastFeeding = food.toFloat / (Const.consumption * (n + y / 2))
      0
    mkLongFeeding()
    nrReturn

  def getDayProduct:Int = scala.math.round(Const.dayProduce*n * lastFeeding)
  def getWeekProduct:Int = scala.math.round(Const.weekProduce*n * lastFeeding)

  def reproduce():Unit =
    weeks -= 1
    if y == 0 then
      y = (Const.reproduceFactor*n).toInt
      weeks = Const.growWeeks
    else if weeks <= 0 then
      n += y
      y = 0
  
  def mkLongFeeding():Unit =
    feeding -= 0.05f*(0.98f - lastFeeding)
    if feeding > 1.0f then feeding = 1.0f
    else if feeding < 0.0f then mkDie()

  def checkLife:Boolean =
    //println("animals are dead")
    n <= 0

  def mkDie():Unit =
    n -= math.ceil(n*0.2).toInt
    y -= math.ceil(y*0.2).toInt
    //println(s"die, old: ${math.ceil(n*0.2).toInt}, young: ${math.ceil(y*0.2).toInt}")
    checkLife

  private def slaughter():(Int, Int) =
    val numS = if n >= maxN then n - maxN else 0
    n -= numS
    (numS*Const.meat, numS*Const.leather)

  def mkSlaughter(items:ItemList): Unit =
    val (m, l) = slaughter()
    if m > 0 && l > 0 then println(s"mkSlaughter $m $l")
    this match {
      case _: Pigs =>
        items.pork  += m
      case _ => 
        items.meat += m
    }
    items.leathers += l

trait  FarmAnimalConst:
  val consumption:Int
  val dayProduce:Int
  val weekProduce:Int
  val reproduceFactor:Float
  val meat:Int
  val leather:Int
  val growWeeks:Int

class Cows extends FarmAnimal:
  override val Const:FarmAnimalConst = CowsConst
class Ship extends FarmAnimal:
  override val Const:FarmAnimalConst = ShipConst
class Goats extends FarmAnimal:
  override val Const:FarmAnimalConst = GoatsConst
class Pigs extends FarmAnimal:
  override val Const:FarmAnimalConst = PigsConst

object CowsConst extends  FarmAnimalConst:
  val consumption = 8
  val dayProduce = 3
  val weekProduce = 1
  val meat: Int =  120
  val leather: Int = 3
  val reproduceFactor = 0.5f
  val growWeeks = 26

object ShipConst extends  FarmAnimalConst:
  val consumption = 4
  val dayProduce = 1
  val weekProduce = 1
  val meat: Int = 35
  val leather: Int = 1
  val reproduceFactor = 0.5f
  val growWeeks = 20

object GoatsConst extends FarmAnimalConst:
  val consumption = 3
  val dayProduce = 1
  val weekProduce = 1
  val meat = 20
  val leather: Int = 1
  val reproduceFactor = 0.5f
  val growWeeks = 18

object PigsConst extends FarmAnimalConst:
  val consumption = 2
  val dayProduce = 0
  val weekProduce = 0
  val meat = 40
  val leather = 5
  val reproduceFactor = 0.7f
  val growWeeks = 16


