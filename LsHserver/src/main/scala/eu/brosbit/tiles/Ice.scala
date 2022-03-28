package eu.brosbit.tiles
import eu.brosbit.immovable._

class Ice extends Tile {
  override val aType: Byte = 0
  override val level: Byte = 1
  override val fertility: Byte = 0
  override val name: String = "ice pool"
  override val shortName: String = "i"
  var imObjOpt:Option[ImmovableObject] = None
} 