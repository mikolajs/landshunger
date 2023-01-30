package eu.brosbit

import eu.brosbit.hexlib.Hex
import eu.brosbit.tiles.Tile

object Main:
  //
  val hexLib = new Hex(40, 40)
  var days = 0
  val dayTime = 1

 
  def main(args: Array[String]): Unit =
    val map = MapGeneratorSteppe(40).getMap
    val plantsManager = new PlantsManager(map)
    val wildAnimals = new WildAnimals(map, 3)
    //val statistics = new Statistics(map)
    println(mkMapString(map))
    val mapManager = MapManager(map, plantsManager, wildAnimals)
    mapManager.nextDay()
    days = 1
    val daysShow = 1

    var play = true

    while(play) do
      println(s"Dzień $days")
      for time <- 1 to dayTime do
        wildAnimals.moveAnimals

      println("STATYSTYKI:")
      //statistics.countForests
      //statistics.countHPOfForest
      //statistics.countGrasses
      //statistics.countBioGrasses
      wildAnimals.showInfoDeer
      //wildAnimals.consumeAndGrown()
      Thread.sleep(1000)
      mapManager.nextDay()
      wildAnimals.nextDay
      days += 1
      if days >= daysShow then play = false


  private def mkMapString(m:Array[Array[Tile]]) =
    val mapString = m.map(line => 
      "[" + 
        line.map(t =>  "'" + t.aType.shortName + "'").mkString(",")
        + "]"
    ).mkString(",")
    "{ 'jsonMap':[" + mapString + "]}"
