package eu.brosbit.tiles
import eu.brosbit.immovable._

class Plain (tileConst:TileConstant) extends Tile(tileConst) {
  var imObjOpt:Option[ImmovableObject] = None
  def nextDay = {}
} 