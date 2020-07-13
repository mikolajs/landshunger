package eu.brosbit


object Main {
  //
 
  def main(args: Array[String]): Unit = {
    val map = new TheMap("map.data")
    val mapArray = map.getMap
    val plantsManager = new PlantsManager(mapArray)
    val statistics = new Statistics(mapArray)
    val mapManager = new MapManager(map, plantsManager)
    mapManager.nextDay()
    for(i <- 1 to 10){
      println(s"Dzień $i")
      //map.printTiles
      statistics.countForests
      statistics.countHPOfForest
      statistics.countGrasses
      statistics.countHPOGrasses
      Thread.sleep(1000)
      mapManager.nextDay()
    }

  }
  
}


