package eu.brosbit.hexlib

import eu.brosbit.movable.MapPosition

class PathFinder(hex:Hex) {

  //TODO: Implement method
  def findWaterPath(from:MapPosition, to:MapPosition):List[MapPosition] = {
    var path:List[MapPosition] = Nil
    val distance = hex.distance(from, to)
    if(distance > 10) return Nil
    path
  }

  //TODO: Implement method
  def findLandPath(from:MapPosition, to: MapPosition):List[MapPosition] = {
    var path:List[MapPosition] = Nil
    val distance = hex.distance(from, to)
    if(distance > 10) return Nil
    path
  }

}
