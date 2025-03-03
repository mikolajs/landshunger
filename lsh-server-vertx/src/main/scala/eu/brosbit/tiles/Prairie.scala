package eu.brosbit.tiles
import eu.brosbit.immovable._

class Prairie extends Tile {
  override val aType: Byte = 0
  override val level: Byte = 1
  override val fertility: Byte = 2
  override val name: String = "Steppe"
  override val shortName: String = "s"
  var imObjOpt:Option[ImmovableObject] = None
}