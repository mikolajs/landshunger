package eu.brosbit.tiles

import eu.brosbit.immovable.*

object Mountain extends TileObj: 
  override val level: Byte = 3
  override val fertility: Byte = 0
  override val name: String = "mountain"
  override val shortName: String = "mo"

class Mountain extends Tile:
  override val aType = Mountain
  var imObjOpt:Option[ImmovableObject] = None
