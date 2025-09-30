package eu.brosbit.lshcore.tiles

import eu.brosbit.lshcore.immovable.*

object Humus  extends TileObj:
  override val level: Byte = 1
  override val fertility: Byte = 0
  override val name: String = "humus"
  override val shortName: String = "hu"

class Humus extends Tile:
  override val aType = Humus
