package eu.brosbit

import eu.brosbit.hexlib.Hex
import eu.brosbit.tiles.Tile

object Main:
  //
  val SIZE = 30
  val hexLib = new Hex(SIZE, SIZE)
  var days = 0
  val dayTime = 1

 
  def main(args: Array[String]): Unit =
    val map = MapGeneratorSteppe(SIZE).getMap
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


  private def mkMapJson(m:Array[Array[Tile]]) =
    val mapString = m.map(line => 
      "[" + 
        line.map(t =>  "'" + t.aType.shortName + "'").mkString(",")
        + "]"
    ).mkString(",")
    "{ 'jsonMap':[" + mapString + "]}"


  private def toViewMapChar(ch:Char) = 
     ch match 
       case c if c == 'p' => '.'
       case c if c == 'w' => '~'
       case c if c == 'h' => 'O'
       case c if c == 'm' => 'X'
       case c if c == 'a' => 'v'
       case c if c == 'f' => '-'
       case c if c == 'l' => '='
       case c if c == 'c' => ';'
       case c if c == 'i' => '*'
       case c => c
     
  private def createInfoTile(tile:Tile) = 
    toViewMapChar(tile.aType.shortName.head)

  
  private def mkMapString(m:Array[Array[Tile]]) = 
    val mapString = m.map(line => line.map(t => toViewMapChar(t.aType.shortName.head)).mkString).mkString("\n")
    mapString
