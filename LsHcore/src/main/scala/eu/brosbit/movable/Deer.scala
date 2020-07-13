package eu.brosbit.movable

class Deer(x:Short, y:Short) extends  Animal {
  val obj = Deer
  override var number: Short = 2
  override var grown: Short = 0
  override var full: Short = 100
  override val position: MapPosition = MapPosition(x, y)
  override var moveTo: List[MapPosition] = Nil

}

object  Deer extends AnimalConst  {
  override val speed: Short = 20
  override val meat: Short = 200
  override val milk: Short = 0
  override val wool: Short = 0
  override val leather: Short = 20
  override val eatSize: Short =  10
}
