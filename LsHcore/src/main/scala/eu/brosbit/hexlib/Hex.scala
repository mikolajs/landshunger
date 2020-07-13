package eu.brosbit.hexlib

import eu.brosbit.movable.MapPosition

class Hex(worldLong:Short, worldWidth:Short) {

  def distance(from:MapPosition, to:MapPosition) = 0
  def neighbours(pos:MapPosition, distance:Int = 1):List[MapPosition] = {
    var n:List[MapPosition] = List()
    n = MapPosition(((pos.x + distance) % worldWidth ).toShort, pos.y)::n
    n = MapPosition(if(pos.x - distance < 0 ) (pos.x + worldWidth - distance).toShort else (pos.x + distance).toShort, pos.y)::n
   // if(pos.y % 2 == 0)
    n
  }
}
