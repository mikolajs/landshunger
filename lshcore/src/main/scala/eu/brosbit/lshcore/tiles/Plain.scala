package eu.brosbit.lshcore.tiles

import eu.brosbit.immovable.*

object  Plain extends TileObj:
  override val level: Byte = 1
  override val fertility: Byte = 9
  override val name: String = "plain"
  override val shortName: String = "pl"


class Plain  extends Tile:
  override val aType = Plain
