package eu.brosbit.lshcore.tiles

import eu.brosbit.lshcore.immovable._

object DeepWater extends TileObj:
  override val level: Byte = 0 
  override val fertility: Byte = 5
  override val name: String = "water"
  override val shortName: String = "wd"

class DeepWater extends Tile:
  override val aType = DeepWater
