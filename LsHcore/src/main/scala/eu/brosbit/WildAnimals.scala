package eu.brosbit

import eu.brosbit.movable.{Animal, Deer, MapPosition}
import eu.brosbit.tiles.Tile

import scala.collection.mutable.ArrayBuffer

class WildAnimals(map:Array[Array[Tile]], units:Array[Array[Boolean]]) {
  var deers:ArrayBuffer[Animal] = new ArrayBuffer[Animal]()
  private val wordSize = map.length

  def createDeerHeard: Unit = {
    val randPoint = MapPosition((Math.random()*2*wordSize).toInt, (Math.random()*wordSize).toInt)
    var tried = 0
    var lines = 0
    while(!canPlaceDeer(randPoint.c, randPoint.r)){
      if(tried >= 2*wordSize) {
        tried = 0
        randPoint.r =  ((randPoint.r+1) % wordSize).toShort
        lines += 1
        if(lines > wordSize) return
      } else tried += 1
      randPoint.c = ((randPoint.c + 1) % (2*wordSize)).toShort
    }
    val newHeard = new Deer(0,0)
  }

  private def canPlaceDeer(c:Int, r:Int): Boolean =
    freeAndPlainOrHill(c, r) && (isWood(c, r) || isGrass(c, r))
  private def canPlaceBuffalo(c:Short, r:Short): Boolean =
    freePlain(c,r) || isGrass(c,r)
  private def canPlacePeccary(c: Short, r:Short): Boolean =
    freeAndPlainOrHill(c, r) && isWood(c, r)

  private def freeAndPlainOrHill(c:Int, r:Int):Boolean = {
    if (units(r)(c)) return false
    val level = map(r)(c).level
    if (level > 2 || level < 1) false else true
  }

  private def freePlain(c: Int, r: Int): Boolean = {
    if (units(r)(c)) return false
    if (map(r)(c).level == 1) true else false
  }

    private def isWood(c:Int, r:Int): Boolean = {
      if(map(r)(c).imObjOpt.isEmpty) return false
      map(r)(c).imObjOpt.get.obj.shortName match {
        case "PL" => true
        case "PW" => true
        case "LW" => true
        case _ => false
      }
    }

  private def isGrass(c:Int, r:Int): Boolean = {
    if(map(r)(c).imObjOpt.isEmpty) return false
    map(r)(c).imObjOpt.get.obj.shortName match {
      case "GR" => true
      case _ => false
    }
  }

}
