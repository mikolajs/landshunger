package eu.brosbit.tiles
import eu.brosbit.immovable._

class Sand(tileConst:TileConstant) extends Tile(tileConst) {
  var imObjOpt:Option[ImmovableObject] = None
  def nextDay = {}
} 