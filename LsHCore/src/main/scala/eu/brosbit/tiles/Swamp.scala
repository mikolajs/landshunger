package eu.brosbit.tiles
import eu.brosbit.immovable._

class Swamp extends Tile {
  override val aType: Byte = 0
  override val level: Byte = 1
  override val fertility: Byte = 0
  override val name: String = "swamp"
  override val shortName: String = "Sw"
  var imObjOpt:Option[ImmovableObject] = None
}