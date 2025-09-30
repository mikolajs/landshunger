package eu.brosbit.lshcore.tiles

import eu.brosbit.lshcore.immovable.*

object ShallowWater extends TileObj:
  override val level: Byte = 0
  override val fertility: Byte = 10
  override val name: String = "shallow water"
  override val shortName: String = "ws"

class ShallowWater extends Tile:
  override val aType = ShallowWater