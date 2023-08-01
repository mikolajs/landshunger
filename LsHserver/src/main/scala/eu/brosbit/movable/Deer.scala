package eu.brosbit.movable

import scala.collection.mutable.ArrayBuffer
import eu.brosbit.hexlib.MapPosition

class Deer(r:Int, c:Int) extends  Animal {
  override val obj = Deer
  number = 2
  grown = 0
  eaten = 0
  override val position: MapPosition = MapPosition(r, c)
  override val moveTo: ArrayBuffer[MapPosition] = ArrayBuffer.empty[MapPosition]
}

object  Deer extends AnimalConst  {
  override val speed: Int = 20
  override val meat: Int = 200
  override val milk: Int = 0
  override val wool: Int = 0
  override val leather: Int = 20
  override val eatSize: Int =  5
  override val maxSize: Int = 20
}
