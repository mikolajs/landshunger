package eu.brosbit.tiles
import eu.brosbit.immovable.*

object  Plain extends TileObj:
  override val level: Byte = 0
  override val fertility: Byte = 9
  override val name: String = "plain"
  override val shortName: String = "p"


class Plain  extends Tile:
  override val aType = Plain
  var imObjOpt:Option[ImmovableObject] = None
