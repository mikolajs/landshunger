package eu.brosbit.tiles
import eu.brosbit.immovable._

class Humus  extends Tile {
  override val aType: Byte = 0
  override val level: Byte = 0
  override val fertility: Byte = 0
  override val name: String = "humus"
  override val shortName: String = "Hu"
  var imObjOpt:Option[ImmovableObject] = None
} 