package eu.brosbit.tiles

import eu.brosbit.immovable.ImmovableObject

class Hill extends Tile {
  override val aType: Byte = 30
  override val level: Byte = 2
  override val fertility: Byte = 5
  override val name: String = "hill"
  override val shortName: String = "h"
  var imObjOpt:Option[ImmovableObject] = None

}