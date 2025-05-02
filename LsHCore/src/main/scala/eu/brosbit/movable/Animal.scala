package eu.brosbit.movable
import scala.collection.mutable.ArrayBuffer
import eu.brosbit.hexlib.MapPosition

trait Animal extends Movable {
  val obj:AnimalConst
  var number:Short
  var grown:Short
  var eaten:Short
  val position: MapPosition 
  val moveTo: ArrayBuffer[MapPosition]
}

trait AnimalConst {
  val speed:Short
  val meat:Short
  val milk:Short
  val wool:Short
  val leather:Short
  val eatSize:Short
}

