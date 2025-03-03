package eu.brosbit.tiles
import eu.brosbit.immovable.*

object ShallowWater extends TileObj:
  override val level: Byte = 0
  override val fertility: Byte = 10
  override val name: String = "shallow water"
  override val shortName: String = "l"

class ShallowWater extends Tile:
  override val aType = ShallowWater
  var imObjOpt:Option[ImmovableObject] = None
