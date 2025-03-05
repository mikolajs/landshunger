package eu.brosbit.tiles
import eu.brosbit.immovable.*

object Humus  extends TileObj:
  override val level: Byte = 0
  override val fertility: Byte = 0
  override val name: String = "humus"
  override val shortName: String = "hu"

class Humus extends Tile:
  override val aType = Humus
  var imObjOpt:Option[ImmovableObject] = None
