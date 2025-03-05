package eu.brosbit.tiles
import eu.brosbit.immovable.*

object Ford extends TileObj:
  override val level: Byte = 0
  override val fertility: Byte = 0
  override val name: String = "ford"
  override val shortName: String = "fd"

class Ford extends Tile:
  override val aType = Ford
  var imObjOpt:Option[ImmovableObject] = None
