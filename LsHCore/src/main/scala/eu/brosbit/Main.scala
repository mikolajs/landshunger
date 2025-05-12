package eu.brosbit

import eu.brosbit.hexlib.Hex
import eu.brosbit.tiles.Tile
import eu.brosbit.Printer

object Main:
  //
  val SIZE = 40
  val hexLib = new Hex(SIZE, SIZE)
  var days = 0
  val dayTime = 1

 
  def main(args: Array[String]): Unit =
    val map = MapGeneratorSteppe(SIZE).getMap
    val plantsManager = new PlantsManager(map)
    val mapUnit:Array[Array[Int]] = Array.ofDim[Int](SIZE,SIZE)
    val wildAnimals = new WildAnimals(map, mapUnit, 3)
    val statistics = new Statistics(map)
    println(mkTileMapString(map))
    statistics.countTiles
    val mapManager = MapManager(map, plantsManager, wildAnimals)
    days = 1
    val daysShow = 5
    var play = true
    while(play) do
      println(s"Dzień $days")
      mapManager.nextDay()

      for time <- 1 to dayTime do
        wildAnimals.moveAnimals

      println("STATYSTYKI:")
      statistics.countForests
      statistics.countHPOfForest
      statistics.countGrasses
      statistics.countBioGrasses
      wildAnimals.showInfoDeer
      //wildAnimals.consumeAndGrown()
      wildAnimals.nextDay
      println(mkMapString(map))
      Thread.sleep(1000)
      days += 1
      if days >= daysShow then play = false


  private def mkMapJson(m:Array[Array[Tile]]) =
    val mapString = m.map(line => 
      "[" + 
        line.map(t =>  "'" + t.aType.shortName + "'").mkString(",")
        + "]"
    ).mkString(",")
    "{ 'jsonMap':[" + mapString + "]}"


  
  private def mkMapString(m:Array[Array[Tile]]) = 
    val mapString = m.map(line => 
        val firstLine = line.map(t => Printer.getFirstLine(t)).mkString("|")
        val secondLine = line.map(t => Printer.getSecondLine(t)).mkString("|")
        firstLine + "\n" + secondLine 
        ).mkString("\n")
    mapString

  private def mkTileMapString(m:Array[Array[Tile]]) = 
    var r = 1
    val mapString = m.map(line => 
        line.map(t => Printer.toViewMapSymbol(t.aType.shortName)).mkString(" ")
        ).map(line => 
          r += 1
          if r % 2 == 0 then line else " "+line
          ).mkString("\n")
    mapString
    
