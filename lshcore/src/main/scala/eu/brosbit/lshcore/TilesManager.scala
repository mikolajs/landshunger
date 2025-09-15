package eu.brosbit.lshcore

import eu.brosbit.lshcore.immovable.{Forest, Grass, ImmovableObject, ImmovableObjectConst, Plankton}
import eu.brosbit.lshcore.tiles.Tile

object TilesManager {
  val tiles = List(Grass, Forest, Plankton)
  val woods = List(Forest)

  def isForest(obj: ImmovableObjectConst):Boolean = woods.map(_.shortName).exists(w => obj.shortName == w)

}
