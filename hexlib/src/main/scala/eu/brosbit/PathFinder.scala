package eu.brosbit

import scala.collection.mutable.Queue


case class MapPositionWithDistance(pos:MapPosition, dist:Int)

class PathFinder(hex:Hex) {


  //TODO: add returns path to go
  def findPath(from:MapPosition, to: MapPosition, map: Array[Array[Int]] ):List[MapPosition] = {
    val x = 10
    val y = 10
    var path:List[MapPosition] = Nil
    val distance:Array[Array[Int]] = Array.ofDim[Int](x,y).map(_.map(_ => Int.MaxValue))

    val toCheck = Queue[MapPosition]()
    toCheck += from
    distance(from.r)(from.c) = 0
    while(toCheck.nonEmpty) do
      val mp = toCheck.front
      toCheck.dequeue
      val neighbouirs = hex.neighbours(mp).map(t => MapPosition(t._1, t._2))
      neighbouirs.foreach(n => {
        if map(n.r)(n.c) > 0 && distance(n.r)(n.c) > distance(mp.r)(mp.c) + map(n.r)(n.c) then
          distance(n.r)(n.c) = distance(mp.r)(mp.c) + map(n.r)(n.c)
          toCheck += n 
      })
      //println("check: " + mp)

    printPath(distance)
    println("Odległość do szukanego pola: " + distance(to.r)(to.c))       
    path
  }

  def printPath(distance:Array[Array[Int]]) = 
    distance.foreach(row => {
      row.foreach(e => if e > 100 then print("  x ") else if e < 10 then print("  " + e + " ") else print(" " + e + " "))
      println()
    })


}
