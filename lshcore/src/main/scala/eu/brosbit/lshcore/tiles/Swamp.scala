package eu.brosbit.lshcore.tiles

import eu.brosbit.immovable._

object Swamp extends TileObj:
  override val level: Byte = 1
  override val fertility: Byte = 0
  override val name: String = "swamp"
  override val shortName: String = "sw"

class Swamp extends Tile:
  override val aType = Swamp