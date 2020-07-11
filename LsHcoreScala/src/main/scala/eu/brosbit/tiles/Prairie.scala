package eu.brosbit.tiles
import eu.brosbit.immovable._

class Prairie extends Tile {
  override val aType: Byte = 0
  override val level: Byte = 1
  override val fertility: Byte = 0
  override val name: String = "prairie"
  override val shortName: String = "Pr"
  var imObjOpt:Option[ImmovableObject] = None
}