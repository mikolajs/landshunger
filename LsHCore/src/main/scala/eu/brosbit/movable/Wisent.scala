package eu.brosbit.movable

import scala.collection.mutable.ArrayBuffer
import eu.brosbit.hexlib.MapPosition

class Wisent(r:Int, c:Int) extends  Animal: 
  override val obj = Wisent 
  var number: Short = 3
  var grown: Short = 0
  var eaten: Short = 0
  override val position: MapPosition = MapPosition(r, c)
  override val moveTo: ArrayBuffer[MapPosition] = ArrayBuffer.empty[MapPosition]

object  Wisent extends AnimalConst:
  override val speed: Short = 10
  override val meat: Short = 400
  override val milk: Short = 0
  override val wool: Short = 0
  override val leather: Short = 30
  override val eatSize: Short =  10
  override val symbol:String = "@"
  override val name:String = "Wisent"

