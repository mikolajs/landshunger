package eu.brosbit

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers



class PathFinderSpec extends  AnyFlatSpec, Matchers:
  val hex = new HexFlat(10, 10)
  val mapDist = List[MapPositionWithDistance](
    MapPositionWithDistance(MapPosition(0, 0), 1), MapPositionWithDistance(MapPosition(0, 1), 1),
    MapPositionWithDistance(MapPosition(0, 2), 1), MapPositionWithDistance(MapPosition(0, 3), -1),
    MapPositionWithDistance(MapPosition(0, 4), -1), MapPositionWithDistance(MapPosition(0, 5), 2),
    MapPositionWithDistance(MapPosition(0, 6), 2), MapPositionWithDistance(MapPosition(0, 7), 1),
    MapPositionWithDistance(MapPosition(0, 8), 1), MapPositionWithDistance(MapPosition(0, 9), 1),
    MapPositionWithDistance(MapPosition(1, 0), 1), MapPositionWithDistance(MapPosition(1, 1), 1),
    MapPositionWithDistance(MapPosition(1, 2), 1), MapPositionWithDistance(MapPosition(1, 3), -1),
    MapPositionWithDistance(MapPosition(1, 4), -1), MapPositionWithDistance(MapPosition(1, 5), 2),
    MapPositionWithDistance(MapPosition(1, 6), 2), MapPositionWithDistance(MapPosition(1, 7), -1),
    MapPositionWithDistance(MapPosition(1, 8), -1), MapPositionWithDistance(MapPosition(1, 9), 1),
    MapPositionWithDistance(MapPosition(2, 0), 1), MapPositionWithDistance(MapPosition(2, 1), 1),
    MapPositionWithDistance(MapPosition(2, 2), 1), MapPositionWithDistance(MapPosition(2, 3), 2),
    MapPositionWithDistance(MapPosition(2, 4), -1), MapPositionWithDistance(MapPosition(2, 5), 2),
    MapPositionWithDistance(MapPosition(2, 6), 2), MapPositionWithDistance(MapPosition(2, 7), 1),
    MapPositionWithDistance(MapPosition(2, 8), 2), MapPositionWithDistance(MapPosition(2, 9), 1),
    MapPositionWithDistance(MapPosition(3, 0), 1), MapPositionWithDistance(MapPosition(3, 1), 1),
    MapPositionWithDistance(MapPosition(3, 2), 1), MapPositionWithDistance(MapPosition(3, 3), 2),
    MapPositionWithDistance(MapPosition(3, 4), 2), MapPositionWithDistance(MapPosition(3, 5), 2),
    MapPositionWithDistance(MapPosition(3, 6), 2), MapPositionWithDistance(MapPosition(3, 7), 1),
    MapPositionWithDistance(MapPosition(3, 8), 1), MapPositionWithDistance(MapPosition(3, 9), 1),
    MapPositionWithDistance(MapPosition(4, 0), 1), MapPositionWithDistance(MapPosition(4, 1), 1),
    MapPositionWithDistance(MapPosition(4, 2), 1), MapPositionWithDistance(MapPosition(4, 3), 1),
    MapPositionWithDistance(MapPosition(4, 4), 1), MapPositionWithDistance(MapPosition(4, 5), 2),
    MapPositionWithDistance(MapPosition(4, 6), 2), MapPositionWithDistance(MapPosition(4, 7), 1),
    MapPositionWithDistance(MapPosition(4, 8), 1), MapPositionWithDistance(MapPosition(4, 9), 1),
    MapPositionWithDistance(MapPosition(5, 0), 1), MapPositionWithDistance(MapPosition(5, 1), 1),
    MapPositionWithDistance(MapPosition(5, 2), 1), MapPositionWithDistance(MapPosition(5, 3), 1),
    MapPositionWithDistance(MapPosition(5, 4), 1), MapPositionWithDistance(MapPosition(5, 5), 1),
    MapPositionWithDistance(MapPosition(5, 6), 1), MapPositionWithDistance(MapPosition(5, 7), 1),
    MapPositionWithDistance(MapPosition(5, 8), 1), MapPositionWithDistance(MapPosition(5, 9), 1),
    MapPositionWithDistance(MapPosition(6, 0), 1), MapPositionWithDistance(MapPosition(6, 1), 1),
    MapPositionWithDistance(MapPosition(6, 2), 1), MapPositionWithDistance(MapPosition(6, 3), 1),
    MapPositionWithDistance(MapPosition(6, 4), 1), MapPositionWithDistance(MapPosition(6, 5), 2),
    MapPositionWithDistance(MapPosition(6, 6), 1), MapPositionWithDistance(MapPosition(6, 7), 1),
    MapPositionWithDistance(MapPosition(6, 8), 1), MapPositionWithDistance(MapPosition(6, 9), 1),
    MapPositionWithDistance(MapPosition(7, 0), 1), MapPositionWithDistance(MapPosition(7, 1), 1),
    MapPositionWithDistance(MapPosition(7, 2), 1), MapPositionWithDistance(MapPosition(7, 3), 1),
    MapPositionWithDistance(MapPosition(7, 4), 2), MapPositionWithDistance(MapPosition(7, 5), 1),
    MapPositionWithDistance(MapPosition(7, 6), 1), MapPositionWithDistance(MapPosition(7, 7), 1),
    MapPositionWithDistance(MapPosition(7, 8), 1), MapPositionWithDistance(MapPosition(7, 9), 1),
    MapPositionWithDistance(MapPosition(8, 0), 1), MapPositionWithDistance(MapPosition(8, 1), 1),
    MapPositionWithDistance(MapPosition(8, 2), 1), MapPositionWithDistance(MapPosition(8, 3), 2),
    MapPositionWithDistance(MapPosition(8, 4), 1), MapPositionWithDistance(MapPosition(8, 5), 2),
    MapPositionWithDistance(MapPosition(8, 6), 2), MapPositionWithDistance(MapPosition(8, 7), 1),
    MapPositionWithDistance(MapPosition(8, 8), 1), MapPositionWithDistance(MapPosition(8, 9), 1),
    MapPositionWithDistance(MapPosition(9, 0), 1), MapPositionWithDistance(MapPosition(9, 1), 1),
    MapPositionWithDistance(MapPosition(9, 2), 2), MapPositionWithDistance(MapPosition(9, 3), -1),
    MapPositionWithDistance(MapPosition(9, 4), -1), MapPositionWithDistance(MapPosition(9, 5), 2),
    MapPositionWithDistance(MapPosition(9, 6), 2), MapPositionWithDistance(MapPosition(9, 7), 1),
    MapPositionWithDistance(MapPosition(9, 8), 1), MapPositionWithDistance(MapPosition(9, 9), 1))
  val dists = mapDist.map(_.dist).toArray
  var e = false
  val distanceMap = Array.ofDim[Int](10, 10)
  for i <- 0 until dists.size do
    if i % 10 == 0 then println("")
    if i % 10 == 0 && e then 
     	print(" ")
     	e = false
    else if i % 10 == 0 then e = true
    if dists(i) < 0 then print(dists(i) + " ") else  print(" " + dists(i) + " ")
    distanceMap(i/10)(i%10) = dists(i) 
  end for
  println()
  
  val pf = PathFinder(hex)
  
  "Path Move" should " from 0,1 to 0,8 have 11 hexs" in {
    val pathList = pf.findPath(MapPosition(0,1), MapPosition(0, 8), distanceMap)
    //println(pathList)
    pathList.size should be (11)  
  }
  it should " from 4, 0 to 1,9 have 13 hexs" in {
    val pathList = pf.findPath(MapPosition(4,0), MapPosition(1, 9), distanceMap)
    pathList.size should be (13)  
  }
  it should "from 0,2 to 0,5 have 8 hexs" in {
    val pathList = pf.findPath(MapPosition(0,2), MapPosition(0, 5), distanceMap)
    pathList.size should be (8)  
  }
  it should "from 1,2 to 4,4 have 5 hexs" in {
    val pathList = pf.findPath(MapPosition(1,2), MapPosition(4, 4), distanceMap)
    //println(pathList)
    pathList.size should be (5)  
  }

end PathFinderSpec
