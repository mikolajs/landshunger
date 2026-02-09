import scala.collection.mutable.ArrayBuffer
import scala.util.Random
import scala.compiletime.ops.double

val rand = Random()

case class CubePoint(x:Int, y:Int, z:Int)

case class MapPosition(var r:Int, var c:Int)

class Hex(rows:Int, cols:Int) {

  def distance(from:MapPosition, to: MapPosition, round:Boolean = false):Int =
    distanceFromCubes(coordinateToCube(from), coordinateToCube(to))

  def neighbours(position: MapPosition, distance: Int = 1, round:Boolean = false): List[MapPosition] = {
    val cubePoints = (for (z <- -distance to distance; x <- -distance to distance) yield {
      val y = -x - z
      CubePoint(x, y, z)
    }).filter(cp => cp.y.abs <= distance && (cp.x != 0 || cp.z != 0))
    //println(cubePoints)
    //println(position)
    //println(cubePoints.map(cubeToCoordinate(_)))
    val hexPoint = cubePoints.map(cubeToCoordinate(_))
    val hexPoint2 = if round then  hexPoint.map(c =>
      MapPosition(position.r + c.r, (position.c + c.c + (position.r & 1 & c.r) + cols) % cols))
    else hexPoint.map(c =>
      MapPosition(position.r + c.r, position.c + c.c + (position.r & 1 & c.r) ))
    hexPoint2.filter(mp => mp.r < rows && mp.r > -1 && mp.c > -1 && mp.c < cols).toList
  }
  
  private def coordinateToCube(p: MapPosition):CubePoint = {
    val x = p.c - (p.r - (p.r & 1))/2
    CubePoint(x, -x - p.r, p.r)
  }

  private def distanceFromCubes(from:CubePoint, to:CubePoint):Int =
      ((from.x - to.x).abs + (from.y - to.y).abs + (from.z - to.z).abs)/2

  private def cubeToCoordinate(c:CubePoint):MapPosition = {
    MapPosition(c.z, c.x + (c.z - (c.z&1))/2)
  }
}

case class Building(name:String)  

class Resources:
  var corn = 10000
  //chees, fish, meat
  val proteins = Array(4000, 1000, 1500)
  val proteinsType = Array("meat", "chees", "fish")
  val proteinsNeedsArray = Array(0.2, 0.15, 0.1)
  //val proteinsNeedSum = proteinsNeedsArray.sum
  var leather = 100
  var wool = 200
  var timber = 5000
  var flax = 1000
  var iron = 200
  var coper = 100
  var silver = 40
  var gold = 20
  var lead = 30
  def remainsProteins =
    val s = proteins.sum
    proteins.map(x => x.toFloat/s.toFloat)
  def mkProteinsInfo = (0 until proteins.length).map(i => s"${proteinsType(i)}:${proteins(i)}").mkString(", ")
  def printInfo = println(s"Corns: $corn, proteins ($mkProteinsInfo), leather:$leather, wood:$wool, timber:$timber, flax:$flax")
 


class City:
  var districts:ArrayBuffer[(Int, Int)] = ArrayBuffer()
  val resources = Resources()
  var nutries = 100.0f
  var calories = 100.0f
  var nutriesGladness = 100.0f
  def mkEat(people:Array[Array[ArrayBuffer[PeopleUnit]]]) = 
    val numbOfPeople = takePeopleNmb(people)
    println(s"Sum of people in city $numbOfPeople")
    val (shortageProteins, malnutrition, glad) = countProteinsNeed(numbOfPeople)
    //if proteins not enough try eat calories from corn
    val needCorn = (numbOfPeople*0.5).toInt + shortageProteins
    val shortageCorn = countShortageFood(needCorn, resources.corn)
    resources.corn -= needCorn - shortageCorn
    // trying compense calorie shortage with proteins
    val shortageCalories = if shortageCorn > 0 && shortageProteins == 0 then
      compliteProteinsNeed(numbOfPeople, shortageCorn)
    else shortageCorn
    val caloriesComplite = (needCorn - shortageCalories)/needCorn
    println(s"caloriesComplite: $caloriesComplite, malnutrition: $malnutrition, nutries gladness $nutriesGladness")
    nutries = (nutries + malnutrition*100.0f)/2.0f
    if nutries > 100.0f then nutries = 100.0f
    if nutries < 0.0f then nutries = 0.0f
    calories = (calories + caloriesComplite*100.0f)/2.0f
    if calories > 100.0f then calories = 100.0f
    if calories < 0.0f then calories = 100.0f
    nutriesGladness = (nutriesGladness + glad*100.0f)/2.0f
    if nutriesGladness > 100.0f then nutriesGladness = 100.0f
    if nutriesGladness < 0.0f then nutriesGladness = 100.0f
      
  def generateMoney(people:Array[Array[ArrayBuffer[PeopleUnit]]]):Int = 
    val numbOfPeople = takePeopleNmb(people)
    scala.math.round((0.1*mvLevel(calories) + 0.2*mvLevel(nutries) + 0.1*mvLevel(nutriesGladness))*numbOfPeople).toInt
    

  def printMorale =
    println(s"calories: $calories, nutries: $nutries, nutries gladness: $nutriesGladness")

  private def mvLevel(level:Float):Float = if level <=  0.2f then 0.0f else level - 0.2f
    
  private def takePeopleNmb(people:Array[Array[ArrayBuffer[PeopleUnit]]]):Int = 
    districts.map(t =>
      val ab = people(t._1)(t._2)
      if ab.nonEmpty && ab.head.name == "rzemieslnicy" then ab.head.nr else 0
    ).sum

  private def countShortageFood(need:Int, stock:Int):Int = 
    if need <= stock then 0 else need - stock

  private def compliteProteinsNeed(peoples:Int, shortage:Int):Int =
    var arrRemains = resources.remainsProteins
    var rest = (0 until resources.proteins.length).map(i =>
      val toEat = (arrRemains(i)*shortage).toInt
      if resources.proteins(i) >= toEat then
        resources.proteins(i) -= toEat
        0
      else 
        val eat = resources.proteins(i)
        resources.proteins(i) = 0
        eat
    ).sum
    if rest <= 0 then 0
    else 
      for i <- 0 until resources.proteins.length do
        val food = resources.proteins(i)
        if rest > 0 && food <= rest then
          rest -= food 
          resources.proteins(i) = 0
        else if rest > 0 && food > rest then
          resources.proteins(i) -= rest
          rest = 0
      rest
          
  private def countProteinsNeed(peoples:Int):(Int, Float, Float) =
    val proteinsNeed = 0.45*peoples
    var glads = 0.0f
    val leftShort = (0 until resources.proteins.length).map(i => 
      val stock = resources.proteins(i)
      val need = (peoples*resources.proteinsNeedsArray(i)).toInt
      val shortage = if need <= stock then 0 else need - stock
      resources.proteins(i) -= (need - shortage)
      glads += (need - shortage).toFloat/need.toFloat
      shortage
      ).sum
    val leftShortAll = compliteProteinsNeed(peoples, leftShort)
    val malnutrition = (proteinsNeed - leftShortAll).toFloat/proteinsNeed.toFloat
    (leftShortAll, malnutrition, glads/3.0f)
    
        

case class PeopleUnit(name:String, var nr:Int)

val objs = List(("kamienica", 1, 4), ("kamienica", 2, 4), ("magazyn", 2, 5), ("kamienica", 2, 6), 
  ("fort", 3, 3), ("ratusz", 3, 4), ("kamienica", 3, 5), ("zamek", 4, 5))

@main
def main():Unit = 
  val city = City()
  val hex = Hex(10, 10)
  val world = Array.ofDim[ArrayBuffer[PeopleUnit]](10, 10)
  var money = 0
  for i <- 0 until 10 do
    for j <- 0 until 10 do
      world(i)(j) = ArrayBuffer()
  val immovableMap = Array.ofDim[Option[Building]](10, 10)
  for ob <- objs do
    val b = Building(ob._1)
    immovableMap(ob._2)(ob._3) = Some(b)
    city.districts += ((ob._2, ob._3))
    if ob._1 == "kamienica" then 
      val pu = PeopleUnit("rzemieslnicy", rand.nextInt(120)+80)
      world(ob._2)(ob._3) += pu
  for day <- (1 to 20) do
    println(s"day: $day")
    city.mkEat(world)
    money += city.generateMoney(world)
    city.resources.printInfo
    city.printMorale
    println(s"money: $money")

  


