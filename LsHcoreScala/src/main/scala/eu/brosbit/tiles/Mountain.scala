package eu.brosbit.tiles

import eu.brosbit.immovable._

class Mountain extends  Tile {
  override val aType: Byte = 40
  override val level: Byte = 3
  override val fertility: Byte = 0
  override val name: String = "mountain"
  override val shortName: String = "Mo"
  var imObjOpt:Option[ImmovableObject] = None
}