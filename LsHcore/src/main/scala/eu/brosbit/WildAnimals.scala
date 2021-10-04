package eu.brosbit

import eu.brosbit.immovable.{Forest, Grass, Plant}
import eu.brosbit.movable.{Deer, MapPosition}

import scala.collection.mutable.ArrayBuffer

class WildAnimals(map:TheMap, nrCreate:Int) {
  private val hexlib = Main.hexLib
  var deerArr:ArrayBuffer[Deer] = new ArrayBuffer[Deer]()
  //++ Peccary, Buffalo, Fish

  private val wordSize = map.getMap.length

  for(_ <- 1 to nrCreate) createDeerHeard

  implicit val orderingTilesBio:BioTileOrdering = new BioTileOrdering



  def getDeer:Array[Deer] = deerArr.toArray

  //when animal don't eat but go to new destination
  def moveAnimals:Unit = {
    // check if they have something to eat
    val deerToMove = deerArr.filter(d => { d.moveTo.length > 0 })


  }

  def nextDay:Unit = {
    calculateForage()
  }

  //they eat some of bio and if they ate all, they looking for moving somewhere else
  def feed():Unit = {
    deerArr.foreach(d => {
      val p = d.position
      val plant = map.getTile(p.r, p.c).imObjOpt.get.asInstanceOf[Plant]
      val toEat = d.number
      if(plant.getBio >= toEat) {
        plant.setBio((plant.getBio - toEat).toShort)
        d.eaten += toEat
      } else {
        findNewDestinationNearOrElseFar(d)
        d.eaten += plant.getBio
        plant.setBio(0)
      }
    })
  }

  private def calculateForage():Unit = {
    deerArr.foreach(d =>{
      d.grown = (d.grown + d.eaten - d.obj.eatSize*d.number/2.0).toShort
      d.eaten = 0.toShort
      d.number = (d.number + d.grown / d.obj.meat).toShort
      d.grown = (d.grown % d.obj.meat).toShort
    })
  }

/* algorithm:
  looking for nearest neighbours if some of it has 10% of bio i choice max bio from them
   else I looking for distance 2 repeat and distance 3 if not succeed.
   I must check if there is path to point of map, and if nobody occupy point

   OR?
   val prior = bio/distance nad go where max
 */
  private def findNewDestinationNearOrElseFar(d:Deer):Unit = {
      val position = (d.position::hexlib.neighbours(d.position))
        .filter(mp => {
          val tile = map.getTile(mp.r, mp.c)
          tile.level > 0 && tile.level < 3 &&
          (tile.imObjOpt.nonEmpty && tile.imObjOpt.get.isInstanceOf[Grass] ||
            tile.imObjOpt.nonEmpty && tile.imObjOpt.get.isInstanceOf[Forest])})
        .filter(mp => !map.getUnitMap(mp.r)(mp.c)).max

         if(map.getTile(position.r, position.c).imObjOpt.get.asInstanceOf[Plant].getBio < 10) {
           findNewDestinationFar(d)
        } else if(position != d.position) d.moveTo += position
  }


  //TODO: implement this method
  private def findNewDestinationFar(deer: Deer):Unit = {

  }


  private def createDeerHeard: Unit = {
    val randPoint = MapPosition((Math.random()*wordSize).toInt, (Math.random()*wordSize).toInt)
    var tried = 0
    var lines = 0
    while(!canPlaceDeer(randPoint.c, randPoint.r)){
      if(tried >= wordSize) {
        tried = 0
        randPoint.r =  ((randPoint.r+1) % wordSize).toShort
        lines += 1
        if(lines > wordSize) return
      } else tried += 1
      randPoint.c = ((randPoint.c + 1) % wordSize).toShort
    }
    val newHeard = new Deer(randPoint.r,randPoint.c)
    deerArr += newHeard
  }

  def addDeerHeard(deer:Deer):Unit = deerArr += deer

  def showInfoDeer:Unit = {
    println(s"Number of Deer: ${deerArr.length}")
    deerArr.foreach(d =>
     println(s"Position: ${d.position} number: ${d.number}")
    )
  }

  private def canPlaceDeer(c:Int, r:Int): Boolean =
    freeAndPlainOrHill(c, r) && (isWood(c, r) || isGrass(c, r))
  private def canPlaceBuffalo(c:Short, r:Short): Boolean =
    freePlain(c,r) || isGrass(c,r)
  private def canPlacePeccary(c: Short, r:Short): Boolean =
    freeAndPlainOrHill(c, r) && isWood(c, r)

  private def freeAndPlainOrHill(c:Int, r:Int):Boolean = {
    if (map.getUnitMap(r)(c)) return false
    val level = map.getTile(r, c).level
    if (level > 2 || level < 1) false else true
  }

  private def freePlain(c: Int, r: Int): Boolean = {
    if (map.getUnitMap(r)(c)) return false
    if (map.getTile(r, c).level == 1) true else false
  }

    private def isWood(c:Int, r:Int): Boolean = {
      if(map.getTile(r, c).imObjOpt.isEmpty) return false
      map.getTile(r, c).imObjOpt.get.obj.shortName match {
        case "G" => true
        case _ => false
      }
    }

  private def isGrass(c:Int, r:Int): Boolean = {
    if(map.getTile(r, c).imObjOpt.isEmpty) return false
    map.getTile(r, c).imObjOpt.get.obj.shortName match {
      case "G" => true
      case _ => false
    }
  }

  class BioTileOrdering extends  Ordering[MapPosition] {
    override def compare(x: MapPosition, y: MapPosition): Int = {
      map.getTile(x.r, x.c).imObjOpt.getOrElse(Grass()).asInstanceOf[Plant].getBio -
      map.getTile(y.r, y.c).imObjOpt.getOrElse(Grass()).asInstanceOf[Plant].getBio

    }
  }

}
