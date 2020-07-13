package eu.brosbit

class MapManager(theMap: TheMap, plantsManager: PlantsManager) {


  def nextDay(): Unit = {
    plantsManager.nextDay()
    for(a <- theMap.getMap; t <- a) t.nextDay
  }

}
