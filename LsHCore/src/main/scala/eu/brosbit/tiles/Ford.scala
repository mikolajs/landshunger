package eu.brosbit.tiles
import eu.brosbit.immovable.*

object Ford extends TileObj:
  override val level: Byte = 0
  override val fertility: Byte = 0
  override val name: String = "Ford"
  override val shortName: String = "f"

class Ford extends Tile:
  override val aType = Ford
  var imObjOpt:Option[ImmovableObject] = None
