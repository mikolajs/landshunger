package eu.brosbit.movable

case class MapPosition(var r:Int, var c:Int)

trait Animal extends Movable {
  val obj:AnimalConst
  var number:Short = 0
  var grown:Short = 0
  var eaten:Short = 0
}

trait AnimalConst {
  val speed:Short
  val meat:Short
  val milk:Short
  val wool:Short
  val leather:Short
  val eatSize:Short
}

