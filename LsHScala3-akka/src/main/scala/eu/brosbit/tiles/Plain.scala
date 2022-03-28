package eu.brosbit.tiles
import eu.brosbit.immovable._

class Plain  extends Tile:
  override val aType:Byte = 10
  override val level: Byte = 1
  override val fertility: Byte = 10
  override val name: String = "plain"
  override val shortName: String = "p"
  var imObjOpt:Option[ImmovableObject] = None
