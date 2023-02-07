package eu.brosbit.tiles
import eu.brosbit.immovable.*

object Swamp extends TileObj:
  override val level: Byte = 1
  override val fertility: Byte = 0
  override val name: String = "swamp"
  override val shortName: String = "sw"

class Swamp extends Tile:
  override val aType = Swamp
  var imObjOpt:Option[ImmovableObject] = None
