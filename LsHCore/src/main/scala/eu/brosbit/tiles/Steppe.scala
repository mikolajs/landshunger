package eu.brosbit.tiles

import eu.brosbit.immovable.*

object Steppe extends TileObj:
  override val level: Byte = 1
  override val fertility: Byte = 2
  override val name: String = "steppe"
  override val shortName: String = "st"

class Steppe extends Tile:
  override val aType = Steppe
  var imObjOpt:Option[ImmovableObject] = None
