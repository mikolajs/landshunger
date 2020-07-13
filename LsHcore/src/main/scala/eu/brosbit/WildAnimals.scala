package eu.brosbit

import eu.brosbit.movable.{Animal, Deer, MapPosition}
import eu.brosbit.tiles.Tile

import scala.collection.mutable.ArrayBuffer

class WildAnimals(map:Array[Array[Tile]], units:Array[Array[Boolean]]) {
  var deers:ArrayBuffer[Animal] = new ArrayBuffer[Animal]()
  val wordSize = map.size

  def createDeerHeard: Unit = {
    val randPoint = MapPosition((Math.random()*2*wordSize).toShort, (Math.random()*wordSize).toShort)
    var tried = 0
    var lines = 0
    while(!canPlaceDeer(randPoint.x, randPoint.y)){
      if(tried >= 2*wordSize) {
        tried = 0
        randPoint.y =  ((randPoint.y+1) % wordSize).toShort
        lines += 1
        if(lines > wordSize) return
      } else tried += 1
      randPoint.x = ((randPoint.x + 1) % (2*wordSize)).toShort
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
      map(y)(x).imObjOpt.get.obj.shortName match {
        case "PL" => true
        case "PW" => true
        case "LW" => true
        case _ => false
      }
    }

  private def isGrass(x:Short, y:Short): Boolean = {
    if(map(y)(x).imObjOpt.isEmpty) return false
    map(y)(x).imObjOpt.get.obj.shortName match {
      case "GR" => true
      case _ => false
    }
  }

}
