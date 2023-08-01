package eu.brosbit.movable
import eu.brosbit.hexlib.MapPosition
import scala.collection.mutable.ArrayBuffer

trait Animal extends Movable:
  val obj:AnimalConst
  var number:Int = 0
  var grown:Int = 0
  var eaten:Int = 0


trait AnimalConst:
  val speed:Int
  val meat:Int
  val milk:Int
  val wool:Int
  val leather:Int
  val eatSize:Int
  val maxSize:Int


