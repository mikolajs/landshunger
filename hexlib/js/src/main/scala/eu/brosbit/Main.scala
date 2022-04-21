package eu.brosbit.hexlib

import scala.scalajs.js.annotation.*

@JSExportTopLevel("hexlib")
object Main:
  def main(args:Array[String]) =
    println("hello Main")

@JSExportTopLevel("HexFlat")
class Hex(rows:Int, cols:Int) extends HexFlat(rows, cols): 
  
  @JSExport
  def getDistance(f:MapPosition, t:MapPosition) = distance(f, t)
  
  @JSExport
  def getNeighbours(p:MapPosition, d:Int) = neighbours(p, d)
  
 
@JSExportTopLevel("PathFinder")
class Path(hex:Hex) extends PathFinder(hex):
  @JSExport
  def getPath(from:MapPosition, to: MapPosition, map: Array[Array[Int]]) = findPath(from, to, map)
  
  

@JSExportTopLevel("MapPosition")
class MapPos(r:Int, c:Int) extends MapPosition(r, c)


