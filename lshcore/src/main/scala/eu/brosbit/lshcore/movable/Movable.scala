package eu.brosbit.lshcore.movable

import scala.collection.mutable.ArrayBuffer
import eu.brosbit.hexlib.MapPosition

trait Movable {
  val position:MapPosition
  val moveTo:ArrayBuffer[MapPosition]
}
