package eu.brosbit.movable

case class MapPosition(var x:Short, var y:Short)

trait Animal {
  var number:Short
  var grown:Short
  var full:Short
  val position:MapPosition
  var moveTo:List[MapPosition]
}

trait AnimalConst {
  val speed:Short
  val meat:Short
  val milk:Short
  val wool:Short
  val leather:Short
  val eatSize:Short
}

