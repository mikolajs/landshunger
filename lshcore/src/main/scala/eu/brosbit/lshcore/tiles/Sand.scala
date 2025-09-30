package eu.brosbit.lshcore.tiles

import eu.brosbit.lshcore.immovable.*

object Sand extends TileObj:
  override val level: Byte = 1
  override val fertility: Byte = 0
  override val name: String = "sand"
  override val shortName: String = "sd"

class Sand extends Tile:
  override val aType = Sand
