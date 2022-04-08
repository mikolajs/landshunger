package eu.brosbit


class HexFlat(rows:Short, cols:Short) extends HexBase {

  def distance(from:MapPosition, to: MapPosition):Int = {
     distanceFromCubes(coordinateToCube(from), coordinateToCube(to))
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
      MapPosition(position.r + c.r, (position.c + c.c + (position.r&1&c.r))))
      .filter(mp => mp.r < rows && mp.r > -1)
      .filter(mp => mp.c < cols && mp.c > -1).toList
  }


}
