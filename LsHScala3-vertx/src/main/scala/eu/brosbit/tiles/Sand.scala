package eu.brosbit.tiles
import eu.brosbit.immovable._

class Sand extends Tile {
  override val aType: Byte = 0
  override val level: Byte = 1
  override val fertility: Byte = 0
  override val name: String = "sand"
  override val shortName: String = "Sa"
  var imObjOpt:Option[ImmovableObject] = None
}