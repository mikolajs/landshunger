package eu.brosbit

import eu.brosbit.immovable.{Forest, Grass, Plant}
import eu.brosbit.movable.{Deer, Boar, Wisent}
import eu.brosbit.tiles.Tile
import eu.brosbit.hexlib.*

import scala.collection.mutable.{ArrayBuffer, ArrayStack}

class WildAnimals(map:Array[Array[Tile]], unitArray:Array[Array[Int]], nrCreate:Int) {
  var deerArr:ArrayBuffer[Deer] = new ArrayBuffer[Deer]()
  var boarArr:ArrayBuffer[Boar] = new ArrayBuffer[Boar]()
  var wisentArr:ArrayBuffer[Wisent] = new ArrayBuffer[Wisent]()
  val rand = scala.util.Random()
  //++ Peccary, Buffalo, Fish
  private val wordSize = map.length
  val hexlib = Hex(wordSize, wordSize)
  for _ <- 1 to nrCreate do
    createHeard("Deer")
    createHeard("Boar")
    createHeard("Wisent")
    

  implicit val orderingTilesBio:BioTileOrdering = new BioTileOrdering


  def getDeer:Array[Deer] = deerArr.toArray

  //when animal don't eat but go to new destination
  def moveAnimals:Unit = 
    // check if they have something to eat
    val deerToMove = deerArr.filter(d => { d.moveTo.length > 0 })



  def nextDay:Unit = 
    calculateForage()
  

  //they eat some of bio and if they ate all, they looking for moving somewhere else
  def feed():Unit = {
    deerArr.foreach(d => {
      val p = d.position
      val plant = map(p.r)(p.c).imObjOpt.get.asInstanceOf[Plant]
      val toEat = d.number
      if(plant.getBio >= toEat) {
        plant.setBio((plant.getBio - toEat).toShort)
        d.eaten = (d.eaten + toEat).toShort
      } else {
        findNewDestinationNearOrElseFar(d)
        d.eaten = (d.eaten + plant.getBio).toShort
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
 
  private def findNewDestinationNearOrElseFar(d:Deer):Unit = {
      val position = (d.position::hexlib.neighbours(d.position))
        .filter(mp => {
          val tile = map(mp.r)(mp.c)
          tile.aType.level > 0 && tile.aType.level < 3 &&
          (tile.imObjOpt.nonEmpty && tile.imObjOpt.get.isInstanceOf[Grass] ||
            tile.imObjOpt.nonEmpty && tile.imObjOpt.get.isInstanceOf[Forest])})
        .filter(mp => !mapUnit(mp.r)(mp.c)).max

         if(map(position.r)(position.c).imObjOpt.get.asInstanceOf[Plant].getBio < 10) {
           findNewDestinationFar(d)
        } else if(position != d.position) d.moveTo += position
  }
*/

  //TODO: implement this method
  private def findNewDestinationFar(deer: Deer):Unit = {
      
  }


  private def createHeard(animal:String): Unit = 
    var r = rand.nextInt(wordSize)
    var c = rand.nextInt(wordSize)
    val randPoint = MapPosition((Math.random()*wordSize).toInt, (Math.random()*wordSize).toInt)
    var tried = 0
    var found = false
    while(tried < wordSize && !canPlaceAnimal(randPoint.c, randPoint.r)) do
      if(tried >= wordSize) then
        r = rand.nextInt(wordSize)
        c = rand.nextInt(wordSize)
        found = true
      else tried += 1
   
    if found then  
      animal match
        case "Deer" => deerArr += Deer(r, c)
        case "Boar" => boarArr += Boar(r, c)
        case "Wisent" => wisentArr += Wisent(r, c)

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
    if (mapUnit(r)(c)) return false
    val level = map(r)(c).aType.level
    if (level > 2 || level < 1) false else true
  }

  private def freePlain(c: Int, r: Int): Boolean = {
    if (mapUnit(r)(c)) return false
    if (map(r)(c).aType.level == 1) true else false
  }

    private def isWood(c:Int, r:Int): Boolean = {
      if(map(r)(c).imObjOpt.isEmpty) return false
      map(r)(c).imObjOpt.get.obj.shortName match {
        case "G" => true
        case _ => false
      }
    }

  private def isGrass(c:Int, r:Int): Boolean = {
    if(map(r)(c).imObjOpt.isEmpty) return false
    map(r)(c).imObjOpt.get.obj.shortName match {
      case "G" => true
      case _ => false
    }
  }

  class BioTileOrdering extends  Ordering[MapPosition] {
    override def compare(x: MapPosition, y: MapPosition): Int = {
      map(x.r)(x.c).imObjOpt.getOrElse(Grass()).asInstanceOf[Plant].getBio -
      map(y.r)(y.c).imObjOpt.getOrElse(Grass()).asInstanceOf[Plant].getBio

    }
  }

}
