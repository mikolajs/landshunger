package eu.brosbit

class MapManager(theMap: TheMap, plantsManager: PlantsManager, wildAnimals: WildAnimals) {

  //def createDeers(n:Int):Unit = (1 to n).foreach(_ => wildAnimals.createDeerHeard)

  def nextDay(): Unit = {
    plantsManager.nextDay()
    wildAnimals.calculateForage()
    for(a <- theMap.getMap; t <- a) t.nextDay
  }

}
