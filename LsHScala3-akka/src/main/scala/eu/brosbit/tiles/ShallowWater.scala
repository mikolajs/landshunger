package eu.brosbit.tiles
import eu.brosbit.immovable._

class ShallowWater extends Tile {
  override val aType: Byte = 0
  override val level: Byte = 0
  override val fertility: Byte = 10
  override val name: String = "shallow water"
  override val shortName: String = "SW"
  var imObjOpt:Option[ImmovableObject] = None
}