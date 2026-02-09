abstract class FarmAnimal:
  val Const:FarmAnimalConst
  var n = 10
  var lastFeeding = 0.0f
  var feeding = 1.0f
  def eat(nr:Int) = 
    val nrReturn = if Const.consumtion <= nr then 
      lastFeeding = 1.0f
      nr - Const.consumtion*n
    else 
      lastFeeding = nr.toFloat / (Const.consumtion*n)
      0
    mkLongFeeding
    nrReturn

  def getDayProduct:Int = scala.math.round(Const.dayProduce*n * lastFeeding) 
  def getWeekProduct:Int = scala.math.round(Const.weekProduce*n * lastFeeding) 

  def reproduce:Unit = 
    n = (n*(1.0f + Const.reproduceFactor)).toInt


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

  def slaughter(numb:Int):(Int, Int) = 
    val numS = if n >= numb then numb else n
    n -= numS
    (numS*Const.meat, numS*Const.leather)


trait  FarmAnimalConst:
  val consumtion:Int
  val dayProduce:Int
  val weekProduce:Int
  val reproduceFactor:Float
  val meat:Int
  val leather:Int

class Cows extends FarmAnimal:
  override val Const = CowsConst
class Ship extends FarmAnimal:
  override val Const = ShipConst
  

object CowsConst extends  FarmAnimalConst:
  val consumtion = 5
  val dayProduce = 3
  val weekProduce = 1
  val meat: Int =  20
  val leather: Int = 3
  val reproduceFactor = 0.2f

object ShipConst extends  FarmAnimalConst:
  val consumtion = 2
  val dayProduce = 1
  val weekProduce = 1
  val meat: Int = 5
  val leather: Int = 1
  val reproduceFactor = 0.2f

def mkSlauther(animal:FarmAnimal):(Int, Int) =
  animal.n match
    case n:Int if n < 15 => (0, 0)
    case n:Int if n < 80 => animal.slaughter(4)
    case n:Int if n < 150 => animal.slaughter(20)
    case n => animal.slaughter(50)
  
@main 
def main():Unit = 
    val cows = Cows()
    val ship = Ship()
    var forage = 50000
    var milk = 0
    var wool = 0
    var meat = 0
    var leather = 0
    for i <- 1 to 100 do
        forage = cows.eat(forage)
        milk += cows.getDayProduct
        forage = ship.eat(forage)
        milk += ship.getDayProduct
        if i % 5 == 0 then
          wool += ship.getWeekProduct
          cows.reproduce
          ship.reproduce
          val (m1, l1) = mkSlauther(cows)
          val (m2, l2) = mkSlauther(ship)
          meat += (m1 + m2)
          leather += (l1 + l2)

    println(s"forage: $forage, milk: $milk wool: $wool")
    println(s"meat: $meat, leather: $leather")
    println(s"Cows ${cows.n}, Ship: ${ship.n}")
  

