import scala.collection.mutable.ArrayBuffer

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

class Soldier(val id:Int, _x:Int, _y:Int, val gr:Char):
  var x = _x
  var y = _y
  val mp = MapPosition(x, y)

case class SoldierUnit(val id:Int, val gr:Char):
  def info = s"id:$id group: $gr"


val soldiers = Array(Soldier(0, 0, 2,'B'), Soldier(1, 1, 1, 'A'), Soldier(2, 1, 2, 'B'), Soldier(3, 1, 3, 'C'),
Soldier(4, 2, 1, 'A'), Soldier(5, 2, 2, 'B'), Soldier(6, 2, 3, 'C'), Soldier(7, 2, 4, 'C'), 
Soldier(8, 2, 7, 'D'), Soldier(9, 3, 5, 'B'), Soldier(10, 3, 6, 'D'), Soldier(11, 3, 7, 'D'), 
Soldier(12, 4, 1, 'B'), Soldier(13, 4, 2, 'C'), Soldier(14, 4, 4, 'A'), Soldier(15, 4, 5, 'A'), 
Soldier(16, 4, 6, 'B'), Soldier(17, 5, 2, 'C'), Soldier(18, 5, 3, 'C'), Soldier(19, 5, 4, 'A'), 
Soldier(20, 5, 5, 'C'), Soldier(21, 5, 6, 'C'), Soldier(22, 6, 3, 'A'), Soldier(23, 6, 4, 'A'),
Soldier(24, 6, 6, 'C'), Soldier(25, 6, 7, 'C'), Soldier(26, 7, 2, 'D'), Soldier(27, 7, 3, 'D'), 
Soldier(28, 7, 6, 'C'), Soldier(29, 8, 2, 'B'), Soldier(30, 8, 3, 'B'), Soldier(31, 8, 4, 'D'), 
Soldier(32, 8, 6, 'A'), Soldier(33, 8, 7, 'C'), Soldier(34, 9, 2, 'B'), Soldier(35, 9, 6, 'A'))
val connections = Array((0, 2, 'B') -> (1, 1, 'A'), (1, 1, 'A') -> (1, 2, 'B'), (1, 1, 'A') -> (2, 2, 'B'),
(1, 2, 'B') -> (1, 3, 'C'), (2, 1, 'A') -> (2, 2, 'B'), (2, 2, 'B') -> (2, 3, 'C'),
(3, 5, 'B') -> (3, 6, 'D'), (3, 5, 'B') -> (4, 5, 'A'), (4, 1, 'B') -> (4, 2, 'C'),
(4, 4, 'A') -> (5, 3, 'C'), (4, 5, 'A') -> (4, 6, 'B'), (4, 5, 'A') -> (5, 5, 'C'),
(4, 6, 'B') -> (5, 5, 'C'), (5, 2, 'C') -> (6, 3, 'A'), (5, 3, 'C') -> (5, 4, 'A'),
(5, 3, 'C') -> (6, 3, 'A'), (5, 3, 'C') -> (6, 4, 'A'), (5, 4, 'A') -> (5, 5, 'C'),
(6, 3, 'A') -> (7, 2, 'D'), (6, 3, 'A') -> (7, 3, 'D'), (6, 4, 'A') -> (7, 4, 'D'),
(7, 2, 'D') -> (8, 2, 'B'), (7, 2, 'D') -> (8, 3, 'B'), (7, 3, 'D') -> (8, 3, 'B'),
(7, 6, 'C') -> (8, 6, 'A'), (8, 3, 'B') -> (8, 4, 'D'), (8, 6, 'A') -> (8, 7, 'C'))

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
  assert(soldiers.length == 36)
  assert((0 until soldiers.length).zip(soldiers).forall(z => z._1 == z._2.id))
  assert(connections.size == 27)
  val world = Array.ofDim[ArrayBuffer[SoldierUnit]](10, 10)
  for r <- 0 until world.length do
    for c <- 0 until world(r).length do
      world(r)(c) = ArrayBuffer[SoldierUnit]()
  for soldier <- soldiers do
    val su = SoldierUnit(soldier.id, soldier.gr)
    world(soldier.x)(soldier.y) += (su)
  //println(hex.neighbours(MapPosition(0, 2)))
  //println(hex.neighbours(MapPosition(1, 1)))
  val conns = createConnections(world).sortWith((t1, t2) => 
    if t1._1 == t2._1 && t1._2 == t2._2 && t1._3 == t2._3 then t1._4 < t2._4
    else if t1._1 == t2._1 && t1._2 == t2._2 then t1._3 < t2._3
    else if t1._1 == t2._1 then t1._2 < t2._2
    else t1._1 < t2._1)
  println(s"number of connections ${conns.length}")
  for conn <- conns do
    println(createEdge(conn))
  



