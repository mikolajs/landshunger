package eu.brosbit

import eu.brosbit.immovable.{Grass, ImmovableObject, ImmovableObjectConst, LeafWood, PalmWood, PineWood, Plankton}
import eu.brosbit.tiles.Tile

object TilesManager {
  val tiles = List(Grass, LeafWood, PalmWood, PineWood, Plankton)
  val woods = List(LeafWood, PalmWood, PineWood)

  def isForest(obj: ImmovableObjectConst):Boolean = woods.map(_.shortName).exists(w => obj.shortName == w)

}
