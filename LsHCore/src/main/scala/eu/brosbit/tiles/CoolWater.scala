package eu.brosbit.tiles
import eu.brosbit.immovable.*

object CoolWater extends TileObj:
  override val level: Byte = 0
  override val fertility: Byte = 0
  override val name: String = "cool water"
  override val shortName: String = "wc"

class CoolWater extends Tile:
  override val aType = CoolWater
  var imObjOpt:Option[ImmovableObject] = None