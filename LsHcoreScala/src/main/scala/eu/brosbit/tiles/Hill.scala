package eu.brosbit.tiles

import eu.brosbit.immovable.ImmovableObject

class Hill(tileConst:TileConstant) extends Tile(tileConst) {
  
  var imObjOpt:Option[ImmovableObject] = None
  def nextDay = {}
}