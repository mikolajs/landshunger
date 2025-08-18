package eu.brosbit.lshcore.tiles

import eu.brosbit.lshcore.immovable.{Grass, ImmovableObject}

object Hill extends TileObj:
  override val level: Byte = 2
  override val fertility: Byte = 5
  override val name: String = "hill"
  override val shortName: String = "hi"

class Hill extends Tile:
  override val aType = Hill
