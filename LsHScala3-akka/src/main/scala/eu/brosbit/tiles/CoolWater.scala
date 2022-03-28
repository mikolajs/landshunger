package eu.brosbit.tiles
import eu.brosbit.immovable.*

class CoolWater extends Tile:
  override val aType: Byte = 0
  override val level: Byte = 0
  override val fertility: Byte = 0
  override val name: String = "cool water"
  override val shortName: String = "wc"
  var imObjOpt:Option[ImmovableObject] = None