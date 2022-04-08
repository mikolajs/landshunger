package eu.brosbit

case class MapPosition(r:Int, c:Int)

case class CubePoint(x:Int, y:Int, z:Int)

trait HexBase {

  protected def coordinateToCube(p: MapPosition):CubePoint = {
    val x = p.c - (p.r - (p.r & 1))/2
    CubePoint(x, -x - p.r, p.r)
  }

  protected def distanceFromCubes(from:CubePoint, to:CubePoint):Int =
      ((from.x - to.x).abs + (from.y - to.y).abs + (from.z - to.z).abs)/2

  protected def cubeToCoordinate(c:CubePoint):MapPosition = {
    MapPosition(c.z, c.x + (c.z - (c.z&1))/2)
  }

}
