package eu.brosbit

import eu.brosbit.tiles.Tile

class MapManager(map: Array[Array[Tile]], plantsManager: PlantsManager, wildAnimals: WildAnimals) {

  //def createDeers(n:Int):Unit = (1 to n).foreach(_ => wildAnimals.createDeerHeard)

  def nextDay(): Unit = {
    plantsManager.nextDay()
    //wildAnimals.calculateForage()
    for(a <- map; t <- a) t.nextDay
  }

}
