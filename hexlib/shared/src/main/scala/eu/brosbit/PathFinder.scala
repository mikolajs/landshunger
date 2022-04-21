package eu.brosbit.hexlib

import scala.collection.mutable.Queue


case class MapPositionWithDistance(pos:MapPosition, dist:Int)

class PathFinder(hex:HexFlat) {


  //TODO: add returns path to go
  def findPath(from:MapPosition, to: MapPosition, map: Array[Array[Int]] ):List[MapPosition] = {
    val x = 10
    val y = 10
    
    val distance:Array[Array[Int]] = Array.ofDim[Int](x,y).map(_.map(_ => Int.MaxValue))

    val toCheck = Queue[MapPosition]()
    toCheck += from
    distance(from.r)(from.c) = 0
    while(toCheck.nonEmpty) do
      val mp = toCheck.front
      toCheck.dequeue
      val neighbours = hex.neighbours(mp).map(t => MapPosition(t._1, t._2))
      neighbours.foreach(n => {
        if map(n.r)(n.c) > 0 && distance(n.r)(n.c) > distance(mp.r)(mp.c) + map(n.r)(n.c) then
          distance(n.r)(n.c) = distance(mp.r)(mp.c) + map(n.r)(n.c)
          toCheck += n 
      })
      //println("check: " + mp)
    
    printPath(distance)
    println("Odległość do szukanego pola: " + distance(to.r)(to.c))       
    if distance(to.r)(to.c) == Int.MaxValue then Nil else countPath(to, distance)
  }

  private def countPath(from:MapPosition, distance:Array[Array[Int]]) = 
    var path:List[MapPosition] = from::Nil
    var check = from
    var f = true
    while(f) do
      val neighbours = hex.neighbours(check)
      val minPos = minHex(neighbours, distance)
      path = minPos::path
      check = minPos
      if distance(check.r)(check.c) == 0 then f = false
    path
      
  private def minHex(n:List[MapPosition], distance:Array[Array[Int]]) =
    var m = distance(n.head.r)(n.head.c)
    var d = n.head
    n.foreach(mp => 
      if m > distance(mp.r)(mp.c) then 
        m = distance(mp.r)(mp.c) 
        d = mp
    )
    d  
    

  def printPath(distance:Array[Array[Int]]) = 
    distance.foreach(row => {
      row.foreach(e => if e > 100 then print("  x ") else if e < 10 then print("  " + e + " ") else print(" " + e + " "))
      println()
    })


}
