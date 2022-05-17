package eu.brosbit.tiles

import eu.brosbit.immovable.ImmovableObject

object Hill extends TileObj:
  override val level: Byte = 2
  override val fertility: Byte = 5
  override val name: String = "hill"
  override val shortName: String = "hi"
  
class Hill extends Tile:
  override val aType = Hill
  var imObjOpt:Option[ImmovableObject] = None
