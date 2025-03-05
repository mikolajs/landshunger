package eu.brosbit.tiles

import eu.brosbit.immovable._

object DeepWater extends TileObj:
  override val level: Byte = 0
  override val fertility: Byte = 5
  override val name: String = "sea"
  override val shortName: String = "wd"

class DeepWater extends Tile:
  override val aType = DeepWater
  var imObjOpt:Option[ImmovableObject] = None
