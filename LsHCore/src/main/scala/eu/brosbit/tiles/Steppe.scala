package eu.brosbit.tiles

import eu.brosbit.immovable.*

object Steppe extends TileObj:
  override val level: Byte = 1
  override val fertility: Byte = 2
  override val name: String = "Steppe"
  override val shortName: String = "s"

class Steppe extends Tile:
  override val aType = Steppe
  var imObjOpt:Option[ImmovableObject] = None
