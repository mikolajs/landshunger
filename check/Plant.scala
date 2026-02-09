class Peasants:
  val const = PeasantConst
  var n = 10
  var working = 0

object PeasantConst:
  val farmWork = 0.7f
  val timberWork = 0.6f
  val mineWork = 0.3f

class ItemsList:
  var forage = 0
  var corn = 0
  var meat = 0
  var chees = 0
  var pork = 0
  var wool = 0
  var timber = 0
  var cheesCow = 0
  var linien = 0
  var grape = 0
  def printInfo = println(s"forage:$forage, corn:$corn, meat:$meat, chees:$chees, pork:$pork, wool:$wool, timber:$timber, cheesC:$cheesCow, linien:$linien, grape:$grape")

abstract class Plant:
  val const:PlantConst
  var hp:Int = 0
  var bio:Int = 0
  var food:Int = 0
  var growUp = false
  var preparedUp = false
  def info = s"n:${const.short},h:$hp,b:$bio,f:$food,gU:$growUp,pU:$preparedUp"

  def nextDay = 
    hp += const.growHp
    bio += const.growBio
    food += const.growFood

  def canHarvest:Boolean
  
  def harvest(p:Peasants, it:ItemsList):Unit = ???

  def prepare(p:Peasants) = ???

trait PlantConst:
  val short:String
  val maxHp:Int
  val growHp:Int
  val maxBio:Int
  val growBio:Int
  val maxFood:Int
  val growFood:Int 
  val timeStart:Float
  val timeHarvest:Float

class Grass extends Plant:
  override val const = GrassConst
  override def nextDay =
    hp += const.growHp
    bio += const.growBio
    if hp > const.maxHp then hp = const.maxHp
    if bio > const.maxBio then bio = const.maxBio

  override def canHarvest = bio >= const.maxBio / 4

  override def harvest(p:Peasants, it:ItemsList):Unit = 
    if p.working > 1 then
      val wf = p.const.farmWork*p.n
      val farm = (wf / const.timeHarvest).toInt
      bio -= farm 
      if bio < 0 then
        it.forage += farm + bio
        bio = 0
      else it.forage += farm
      p.working -= 1


object GrassConst extends PlantConst:
  val short = "G"
  val maxHp = 0
  val growHp = 0
  val maxBio = 200
  val growBio = 5
  val maxFood = 0
  val growFood = 0
  val timeStart:Float = 0.01f
  val timeHarvest:Float = 2.4f

class LeafWood extends Plant:
  override val const = LeafWoodConst
  override def nextDay =
    hp += const.growHp
    bio += const.growBio
    food += const.growFood
    if hp > const.maxHp then hp = const.maxHp
    if bio > const.maxBio then bio = const.maxBio
    if food > const.maxFood then food = const.maxFood

  override def canHarvest = hp > 40 

  override def harvest(p:Peasants, it:ItemsList):Unit = 
    if p.working > 1 then
      val wf = p.const.timberWork*p.n
      val tb1 = (wf / const.timeHarvest).toInt
      val tb2 = if tb1 > hp - 5 then hp - 5 else tb1
      hp -= tb2
      it.timber += tb2
      p.working -= 1


object LeafWoodConst extends PlantConst:
  val short = "L"
  val maxHp = 200
  val growHp = 2
  val maxBio = 30
  val growBio = 3
  val maxFood = 20
  val growFood = 2
  val timeStart:Float = 0.1f
  val timeHarvest:Float = 3.5f

class PineWood extends Plant:
  override val const = PineWoodConst
  override def nextDay =
    hp += const.growHp
    bio += const.growBio
    food += const.growFood
    if hp > const.maxHp then hp = const.maxHp
    if bio > const.maxBio then bio = const.maxBio
    if food > const.maxFood then food = const.maxFood

  override def canHarvest = hp > 35 

  override def harvest(p:Peasants, it:ItemsList):Unit = 
    if p.working > 1 then
      val wf = p.const.timberWork*p.n
      val tb1 = (wf / const.timeHarvest).toInt
      val tb2 = if tb1 > hp - 5 then hp - 5 else tb1
      hp -= tb2
      it.timber += tb2
      p.working -= 1


object PineWoodConst extends PlantConst:
  val short = "P"
  val maxHp = 180
  val growHp = 2
  val maxBio = 30
  val growBio = 3
  val maxFood = 16
  val growFood = 2
  val timeStart:Float = 0.1f
  val timeHarvest:Float = 3.2f
  
abstract class Cultivation extends Plant:
  override def canHarvest = growUp

  def createField(p:Peasants) = 
    if p.working > 0 then
      val wf = (p.n*p.const.farmWork).toInt
      val sw = (wf / const.timeStart).toInt
      hp += sw
      if hp >= const.maxHp then
        hp = const.maxHp
        preparedUp = true

  override def nextDay =
    if preparedUp && !growUp then
      bio += const.growBio
      food += const.growFood
      if hp > const.maxHp then hp = const.maxHp
      if bio > const.maxBio then bio = const.maxBio
      if hp >= const.maxHp && bio >= const.maxBio then growUp = true

  override def harvest(p:Peasants, it:ItemsList):Unit = 
    if p.working > 1 then
      val wf = p.const.farmWork*p.n
      val tb1 = (wf / const.timeHarvest).toInt
      val hh = bio + food
      val tb2 = if tb1 >= hh then hh else tb1
      val bio2 = tb2 * bio / hh
      val food2 = tb2 * food / hh
      bio -= bio2
      food -= food2
      it.corn += food2
      it.forage += bio2
      if food <= 0 then 
        food = 0
        growUp = false
        preparedUp = false
        hp = 1
      if bio < 0 then bio = 0
      p.working -= 1
  
class Wheat extends Cultivation:
  override val const = WheatConst

object WheatConst extends PlantConst:
  val short = "W"
  val maxHp = 20
  val growHp = 2
  val maxBio = 10
  val growBio = 1
  val maxFood = 120 
  val growFood = 5
  val timeStart:Float = 2.1f
  val timeHarvest:Float = 1.6f

class Oak extends Cultivation:
  override val const = OakConst

object OakConst extends PlantConst:
  val short = "O"
  val maxHp = 15
  val growHp = 2
  val maxBio = 40
  val growBio = 4
  val maxFood = 40 
  val growFood = 4
  val timeStart:Float = 1.7f
  val timeHarvest:Float = 1.4f

/////////////////////
@main def main():Unit = 
  val peasants = Peasants()
  peasants.n = 120
  val items = ItemsList()
  val plants = Array(Grass(), Grass(), Grass(), Grass(), Grass(), LeafWood(), LeafWood(), PineWood(), PineWood(), 
    Wheat(), Wheat(), Oak(), Oak())
  for day <- 1 to 100 do
      println(s"Day $day, plants ${plants.size}")        
      println(plants.map(p => p.info).mkString("||"))
      peasants.working = 12
      for min <- 1 to 10 do
        plants.foreach(p => 
            if p.canHarvest then p.harvest(peasants, items)
            else if p.isInstanceOf[Cultivation] then p.asInstanceOf[Cultivation].createField(peasants))
      plants.foreach(p => p.nextDay)
      items.printInfo
      println(s"working last: ${peasants.working}")


