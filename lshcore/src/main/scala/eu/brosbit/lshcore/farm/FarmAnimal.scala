package eu.brosbit.lshcore.farm

abstract class FarmAnimal:
  val Const:FarmAnimalConst
  var n = 10
  var y = 10
  var weeks = 0
  var lastFeeding = 0.0f
  var feeding = 1.0f
  var expectedN = 50
  def eat(nr:Int) =
    val nrReturn = if Const.consumption <= nr then
      lastFeeding = 1.0f
      nr - Const.consumption*n
    else
      lastFeeding = nr.toFloat / (Const.consumption*n)
      0
    mkLongFeeding
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



  def mkLongFeeding:Unit =
    feeding -= 0.05f*(0.98f - lastFeeding)
    if feeding > 1.0f then feeding = 1.0f
    else if feeding < 0.0f then mkDie

  def checkLife:Boolean =
    println("animals are dead")
    n <= 0

  def mkDie:Unit =
    n -= (n*0.1).toInt
    println(s"die: ${math.ceil(n*0.1).toInt}")
    checkLife

  private def slaughter(numb:Int):(Int, Int) =
    val numS = if n >= numb then numb else n
    n -= numS
    (numS*Const.meat, numS*Const.leather)
  
  def mkSlauther():(Int, Int) =
    n match
      case n:Int if n < expectedN => (0, 0)
      case n => slaughter(n-expectedN)


trait  FarmAnimalConst:
  val consumption:Int
  val dayProduce:Int
  val weekProduce:Int
  val reproduceFactor:Float
  val meat:Int
  val leather:Int
  val growWeeks:Int


