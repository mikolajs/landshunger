package eu.brosbit.movable

import scala.collection.mutable.ArrayBuffer

class Deer(r:Int, c:Int) extends  Animal {
  override val obj = Deer
  number = 2
  grown = 0
  eaten = 0
  override val position: MapPosition = MapPosition(r, c)
  override val moveTo: ArrayBuffer[MapPosition] = ArrayBuffer.empty[MapPosition]
}

object  Deer extends AnimalConst  {
  override val speed: Short = 20
  override val meat: Short = 200
  override val milk: Short = 0
  override val wool: Short = 0
  override val leather: Short = 20
  override val eatSize: Short =  5
}
