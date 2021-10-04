package eu.brosbit.movable

import scala.collection.mutable.ArrayBuffer

trait Movable {
  val position:MapPosition
  val moveTo:ArrayBuffer[MapPosition]
}
