package eu.brosbit.lshcore.movable

import scala.collection.mutable.ArrayBuffer
import eu.brosbit.hexlib.MapPosition

class Deer(r:Int, c:Int) extends  Animal: 
  override val obj = Deer
  var number: Short = 2
  var grown: Short = 0
  var eaten: Short = 0
  override val position: MapPosition = MapPosition(r, c)
  override val moveTo: ArrayBuffer[MapPosition] = ArrayBuffer.empty[MapPosition]

object  Deer extends AnimalConst: 
  override val speed: Short = 20
  override val meat: Short = 200
  override val milk: Short = 0
  override val wool: Short = 0
  override val leather: Short = 20
  override val eatSize: Short =  5
  override val symbol:String = "$"
  override val name:String = "Deer"

