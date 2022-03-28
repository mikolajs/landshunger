package eu.brosbit.tiles
import eu.brosbit.immovable._

class Ford extends Tile {
  override val aType: Byte = 0
  override val level: Byte = 0
  override val fertility: Byte = 0
  override val name: String = "Ford"
  override val shortName: String = "f"
  var imObjOpt:Option[ImmovableObject] = None
}