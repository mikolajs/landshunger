package eu.brosbit

import eu.brosbit.immovable.{Grass, ImmovableObject, ImmovableObjectConst, Forest, Plankton}
import eu.brosbit.tiles.Tile

object TilesManager {
  val tiles = List(Grass, Forest, Plankton)
  val woods = List(Forest)

  def isForest(obj: ImmovableObjectConst):Boolean = woods.map(_.shortName).exists(w => obj.shortName == w)

}
