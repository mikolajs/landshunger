package eu.brosbit.hexlibjs

import eu.brosbit.hexlib.*
object HexLibrary:
  private var hex: Hex = Hex(10, 10)
  private var pathFinder: PathFinder = PathFinder(hex)
  def init(rows:Int, cols:Int) =
    hex = Hex(rows, cols)
    pathFinder = PathFinder(hex)
  def distance(r1:Int, c1:Int, r2:Int, c2:Int) = hex.distance(MapPosition(r1, c1), MapPosition(r2, c2))
  def neighbours(row:Int, col:Int, distance:Int) = hex.neighbours(MapPosition(row, col), distance)
  def findPath(r1:Int, c1:Int, r2:Int, c2:Int, map:Array[Array[Int]]) =
     pathFinder.findPath(MapPosition(r1, c1), MapPosition(r2, c2), map)
  def countPath(path:List[MapPosition], map:Array[Array[Int]]) = pathFinder.countPath(path, map)

end HexLibrary
