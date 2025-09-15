package eu.brosbit.lshcore

import eu.brosbit.hexlib.*
import eu.brosbit.lshcore.immovable.{Forest, Grass, ImmovableObject, Plankton, Plant, Wasteland}
import eu.brosbit.lshcore.tiles.{CoolWater, DeepWater, Ford, Hill, Humus, Plain, ShallowWater, Steppe, Tile}
import eu.brosbit.tiles.*

class PlantsManager(tileMap:Array[Array[Tile]]) {
  private val waters = tileMap.flatten.count(_.aType.level == 0)
  private val plainAndHills = tileMap.flatten.count(t => t.aType.level > 0 && t.aType.level < 3)
  private val hexlib = Hex(tileMap.size, tileMap.head.size)
  val objectMap: Array[Array[ImmovableObject]] = createPlantsMap 
  createBeginWoods()
  
  
  private def createPlantsMap: Array[Array[ImmovableObject]] = {
    for a <- tileMap yield 
      for t <- a yield { 
        t.aType.name match {
          case n if n == Plain.name => Grass()
          case n if n == Hill.name => Grass()
          case n if n == Humus.name => Grass()
          case n if n == Steppe.name => Grass()
          case n if n == CoolWater.name => Plankton()
          case n if n == DeepWater.name => Plankton()
          case n if n == ShallowWater.name => Plankton()
          case n if n == Ford.name => Plankton()
          case _ => Wasteland()
        }
      }
  }

  def nextDay(): Unit = {
    setWoodsInRandomPlaces(5)
    expandForest()
    growPlants()
  }
  
  private def growPlants(): Unit = {
    for r <- objectMap.indices 
      c <- objectMap(r).indices do
      objectMap(r)(c).nextDay()
  }

  //create new random wood 
  private def setWoodsInRandomPlaces(number:Int): Unit = {
    var grasses:List[(Int, Int)] = List()
    for r <- tileMap.indices do
      for c <- tileMap(r).indices do
        if objectMap(r)(c).isInstanceOf[Grass] && getPercentBio(objectMap(r)(c).asInstanceOf[Plant]) < 0.2f then
          grasses = (r,c)::grasses
    
    val toSetForest = scala.util.Random.shuffle(grasses).take(number)
    for t <- toSetForest do 
      objectMap(t._1)(t._2) = Forest()
  }
  
  private def createBeginWoods():Unit = setWoodsInRandomPlaces(tileMap.length*tileMap.length / 80) 
          
  //create new forest near all forests - sieve woods
  private def expandForest() : Unit = 
    for r <- objectMap.indices do
      for c <- objectMap(r).indices do
        objectMap(r)(c) match {
          case plant: Forest =>
            if getPercentHP(plant) > 0.9f then
              val possible = hexlib.neighbours(MapPosition(r, c)).filter(mp =>
                objectMap(mp.r)(mp.c).obj.name == Grass.name)
              if possible.nonEmpty then
                val mp = possible((Math.random() * possible.size).toInt)
                objectMap(mp.r)(mp.c) = Forest()
          case _ =>
        }

          
  private def getPercentBio(plant: Plant): Float = plant.getBio.toFloat / plant.obj.maxBio.toFloat
  private def getPercentHP(plant: Plant): Float = plant.getHP.toFloat / plant.obj.maxHP.toFloat
}
