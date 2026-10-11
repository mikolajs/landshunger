import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Buffer
import scala.jdk.CollectionConverters._
import scala.languageFeature.experimental.macros
import scala.compiletime.ops.double

case class CubePoint(x:Int, y:Int, z:Int)

case class MapPosition(var r:Int, var c:Int)

class Hex(rows:Int, cols:Int) {

///TODO: eliminate round world
  def distance(from:MapPosition, to: MapPosition, round:Boolean = false):Int =
    if round && (from.c - to.c).abs > cols/2 then
      if to.c > from.c then distanceFromCubes(coordinateToCube(from), coordinateToCube(MapPosition(to.r, to.c-cols)))
      else distanceFromCubes(coordinateToCube(from), coordinateToCube(MapPosition(to.r, to.c+cols)))
    else  distanceFromCubes(coordinateToCube(from), coordinateToCube(to))


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

abstract class Soldier(val _id:Int, _x:Int, _y:Int, val owner:Char, val typeS:String):
  var x = _x
  var y = _y
  val mp = MapPosition(x, y)
  var nr:Int

class Cavalary(val id:Int, _x:Int, _y:Int, owner:Char) extends Soldier(id, _x, _y, owner, "K"):
  var nr = 60
class Shooter(val id:Int, _x:Int, _y:Int, owner:Char) extends Soldier(id, _x, _y, owner, "S"):
  var nr = 120
class Infantry(val id:Int, _x:Int, _y:Int, owner:Char) extends Soldier(id, _x, _y, owner, "P"):
  var nr = 120
class Artillery(val id:Int, _x:Int, _y:Int, owner:Char) extends Soldier(id, _x, _y, owner, "A"):
  var nr = 48
  var barrels = 8


case class SoldierUnit(val id:Int, val gr:Char):
  def info = s"id:$id group: $gr"

class SoldierStack(owner:Char):
  val stack = ArrayBuffer[Soldier]()

//val ta1 = SoldierStack('A')
//ta1.stack.addAll(Array(Soldier(1, )))
def loadSoldierFromFile(fileName:String):Buffer[SoldierStack] =
  val fp = java.nio.file.Paths.get(fileName)
  var id = 0
  var A, B, C, D = 0
  val soldierStack = java.nio.file.Files.readAllLines(fp).asScala.filter(line => line.length > 6 && line(0) != '#').map(line =>
    val data = line.split(" ").toArray
    val team = data(0)(0)
    //val id = data(0).drop(1).toInt
    val row = data(1).toInt
    var col = data(2).toInt
    val untis = data.drop(3)
    var nr = 0
    team match
        case 'A' => nr = A
        case 'B' => nr = B
        case 'C' => nr = C
        case 'D' => nr = D  
        case _ =>   
    val us = for un <- untis yield
      nr += 1
      un match
        case "K" =>  Cavalary(nr, row, col, team)
        case "P" => Infantry(nr, row, col, team)
        case "S" => Shooter(nr, row, col, team)
        case _ => Artillery(nr, row, col, team)
    team match
        case 'A' => A = nr
        case 'B' => B = nr
        case 'C' => C = nr
        case 'D' => D = nr 
        case _ =>   
    val ab = ArrayBuffer[SoldierStack]()
    val ss = SoldierStack(team)
    ss.stack.addAll(us)
    ss
  )
  soldierStack
  
def createBufforsByTeams(buffSold:Buffer[SoldierStack]):Array[ArrayBuffer[Soldier]] =
  val teamsSoldiers = Array.ofDim[ArrayBuffer[Soldier]](4)
  for i <- 0 until teamsSoldiers.length do
    teamsSoldiers(i) = ArrayBuffer[Soldier]()
  buffSold.map(soldierStack =>
    val team = soldierStack.stack.head.owner
    val i = team.toInt - 65
    for soldier <- soldierStack.stack do
      teamsSoldiers(i) += soldier
  )
  teamsSoldiers

def mkOrderedMapPos(mp1:MapPosition, mp2:MapPosition) =
  if mp1.r < mp2.r then
    (mp1, mp2)
  else if mp2.r < mp1.r then
    (mp2, mp1)
  else if mp1.c < mp2.c then
    (mp1, mp2)
  else
    (mp2, mp1)


def createConnections(soldiersMap:Array[Array[ArrayBuffer[SoldierUnit]]]):Array[(Int, Int, Int, Int)] =
  val hex = Hex(10, 10)
  val set = scala.collection.mutable.Set[(Int, Int, Int, Int)]()
  for r <- 0 until soldiersMap.length do
    //println(s"$r arr ${soldiersMap(r).length}")
    for c <- 0 until soldiersMap(r).length do
      if soldiersMap(r)(c).nonEmpty then 
        //println(s"($r, $c) ${soldiersMap(r)(c).get.info}")
        val mpBase = MapPosition(r, c)
        val neighbours = hex.neighbours(mpBase)
        for mp <- neighbours do
          if soldiersMap(mp.r)(mp.c).nonEmpty && soldiersMap(mp.r)(mp.c).head.gr != soldiersMap(r)(c).head.gr then
            val ordMPT = mkOrderedMapPos(mpBase, mp)
            val ordT = (ordMPT._1.r, ordMPT._1.c, ordMPT._2.r, ordMPT._2.c)
            set.add(ordT)
  set.toArray

def createEdge(t:(Int, Int, Int, Int))=
  s"(${t._1}, ${t._2}) -> (${t._3}, ${t._4})"

@main
def main():Unit = 
 //val worldMap = Array.ofDim[Some[SoldierStack]](10)(10)
  val ss = loadSoldierFromFile("soldiers.txt")
  val array = createBufforsByTeams(ss)
  //Ilość jednostek
  assert(array(0).length == 45)
  assert(array(1).length == 39)
  assert(array(2).length == 11)
  assert(array(3).length == 8)
  // assert((0 until soldiers.length).zip(soldiers).forall(z => z._1 == z._2.id))
  // assert(connections.size == 27)
  // val world = Array.ofDim[ArrayBuffer[SoldierUnit]](10, 10)
  // for r <- 0 until world.length do
  //   for c <- 0 until world(r).length do
  //     world(r)(c) = ArrayBuffer[SoldierUnit]()
  // for soldier <- soldiers do
  //   val su = SoldierUnit(soldier.id, soldier.gr)
  //   world(soldier.x)(soldier.y) += (su)
  // //println(hex.neighbours(MapPosition(0, 2)))
  // //println(hex.neighbours(MapPosition(1, 1)))
  // val conns = createConnections(world).sortWith((t1, t2) => 
  //   if t1._1 == t2._1 && t1._2 == t2._2 && t1._3 == t2._3 then t1._4 < t2._4
  //   else if t1._1 == t2._1 && t1._2 == t2._2 then t1._3 < t2._3
  //   else if t1._1 == t2._1 then t1._2 < t2._2
  //   else t1._1 < t2._1)
  // println(s"number of connections ${conns.length}")
  // for conn <- conns do
  //   println(createEdge(conn))

// main()
