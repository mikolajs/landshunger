package eu.brosbit

import eu.brosbit.hexlib.Hex

object Main:
  //
  val hexLib = new Hex(40, 40)

  var days = 0
  val dayTime = 720

 
  def main(args: Array[String]): Unit =
    val map = new TheMap("map.data")
    val plantsManager = new PlantsManager(map)
    val wildAnimals = new WildAnimals(map, 3)
    val statistics = new Statistics(map)
    val mapManager = new MapManager(map, plantsManager, wildAnimals)
    mapManager.nextDay()
    days = 1
    val daysShow = 10

    var play = true

    while(play) {
      println(s"Dzień $days")
      for (time <- 1 to dayTime) {
        wildAnimals.moveAnimals
      }
      println("STATYSTYKI:")
      statistics.countForests
      statistics.countHPOfForest
      statistics.countGrasses
      statistics.countBioGrasses
      wildAnimals.showInfoDeer
      //wildAnimals.consumeAndGrown()
      Thread.sleep(1000)
      mapManager.nextDay()
      wildAnimals.nextDay

      if days >= daysShow then play = false







  
}


