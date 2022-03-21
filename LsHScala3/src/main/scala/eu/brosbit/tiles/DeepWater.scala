package eu.brosbit.tiles

import eu.brosbit.immovable._

class DeepWater extends Tile  {
  override val aType: Byte = 0
  override val level: Byte = 0
  override val fertility: Byte = 5
  override val name: String = "sea"
  override val shortName: String = "wd"
  var imObjOpt:Option[ImmovableObject] = None
}