
object HexMath {
  val cubeNeighbours = for{x <- -1 to 1; y <- -1 to 1 if x != y} yield CubeCoordinate(x, y, -(x + y))
  def distanceFromCube(cc:CubeCoordinate) = (cc.x.abs + cc.y.abs + cc.z.abs)/2

  def toCubeFromTable(tc:TableCoordinate) = CubeCoordinate(tc.col - (tc.row - (tc.row&1)) / 2, (tc.row - (tc.row&1)) / 2 - tc.col - tc.row, tc.row)

  def toTableFromCube(cc:CubeCoordinate) = TableCoordinate(cc.x + (cc.z - cc.z&1), cc.z/2)

  def substact(minuend:CubeCoordinate, subtrahend:CubeCoordinate) = CubeCoordinate(minuend.x - subtrahend.x, minuend.y - minuend.y, minuend.z - minuend.z)

  def distanceBeetween(tcFrom:TableCoordinate, tcTo:TableCoordinate) = {
    distanceFromCube(substact(toCubeFromTable(tcFrom), toCubeFromTable(tcTo)))
  }
}
case class CubeCoordinate(x:Int, y:Int, z:Int)
case class TableCoordinate(col:Int, row:Int)


