package eu.brosbit

case class MapPosition(r:Int, c:Int)

case class CubePoint(x:Int, y:Int, z:Int)

class Hex(rows:Short, cols:Short) {

  def distance(from:MapPosition, to: MapPosition):Int = {
    if((from.c - to.c).abs > cols/2) {
      if(to.c > from.c) distanceFromCubes(coordinateToCube(from), coordinateToCube(MapPosition(to.r, to.c-cols)))
      else distanceFromCubes(coordinateToCube(from), coordinateToCube(MapPosition(to.r, to.c+cols)))
    }
    else  distanceFromCubes(coordinateToCube(from), coordinateToCube(to))
  }
  def neighbours(position: MapPosition, distance: Int = 1) = {
    val cubePoints = (for(z <- -distance to distance; x <- -distance to distance) yield {
        val y = -x - z
        CubePoint(x, y, z)
      }).filter(cp => cp.y.abs <= distance && (cp.x != 0 || cp.z != 0))
    //println(cubePoints)
    //println(position)
    //println(cubePoints.map(cubeToCoordinate(_)))
    cubePoints.map(cubeToCoordinate(_)).map(c =>
      MapPosition(position.r + c.r, (position.c + c.c + (position.r&1&c.r) + cols ) % cols ))
      .filter(mp => mp.r < rows && mp.r > -1).toList
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
