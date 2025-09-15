package eu.brosbit.lshcore.movable

import scala.collection.mutable.ArrayBuffer
import eu.brosbit.hexlib.MapPosition

class Boar(r:Int, c:Int) extends  Animal:
  override val obj = Boar 
  var number: Short = 3
  var grown: Short = 0
  var eaten: Short = 0
  override val position: MapPosition = MapPosition(r, c)
  override val moveTo: ArrayBuffer[MapPosition] = ArrayBuffer.empty[MapPosition]

object  Boar extends AnimalConst:
  override val speed: Short = 10
  override val meat: Short = 200
  override val milk: Short = 0
  override val wool: Short = 0
  override val leather: Short = 20
  override val eatSize: Short =  5
  override val symbol:String = "&"
  override val name:String = "Boar"

