package eu.brosbit.tiles
import eu.brosbit.immovable.*

object Ice extends TileObj:
  override val level: Byte = 1
  override val fertility: Byte = 0
  override val name: String = "ice pool"
  override val shortName: String = "ic"

class Ice extends Tile:
  override val aType = Ice
  var imObjOpt:Option[ImmovableObject] = None