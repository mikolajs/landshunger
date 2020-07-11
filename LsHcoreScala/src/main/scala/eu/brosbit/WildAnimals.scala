package eu.brosbit

import eu.brosbit.movable.{Animal, Deer, MapPosition}
import eu.brosbit.tiles.Tile

import scala.collection.mutable.ArrayBuffer

class WildAnimals(map:Array[Array[Tile]], units:Array[Array[Boolean]]) {
  var deers:ArrayBuffer[Animal] = new ArrayBuffer[Animal]()

  def createDeerHeard = {
    val randPoint = MapPosition((Math.random()*map(0).size).toShort, (Math.random()*map.size).toShort)

    while(!canPlaceDeer(randPoint.x, randPoint.y)){
     randPoint
    }
    val newHeard = new Deer(0,0)
  }

  private def canPlaceDeer(x:Short, y:Short): Boolean =
    freeAndPlainOrHill(x, y) && (isWood(x, y) || isGrass(x, y))
  private def canPlaceBuffalo(x:Short, y:Short): Boolean =
    freePlain(x,y) || isGrass(x,y)
  private def canPlacePeccary(x: Short, y:Short): Boolean =
    freeAndPlainOrHill(x, y) && isWood(x, y)

  private def freeAndPlainOrHill(x:Short, y:Short):Boolean = {
    if (units(y)(x)) return false
    val level = map(y)(x).level
    if (level > 2 || level < 1) false else true
  }

  private def freePlain(x: Short, y: Short): Boolean = {
    if (units(y)(x)) return false
    if (map(y)(x).level == 1) true else false
  }

    private def isWood(x:Short, y:Short): Boolean = {
      if(map(y)(x).imObjOpt.isEmpty) return false
      map(y)(x).imObjOpt.get.shortName match {
        case "PL" => true
        case "PW" => true
        case "LW" => true
        case _ => false
      }
    }

  private def isGrass(x:Short, y:Short): Boolean = {
    if(map(y)(x).imObjOpt.isEmpty) return false
    map(y)(x).imObjOpt.get.shortName match {
      case "GR" => true
      case _ => false
    }
  }

}
