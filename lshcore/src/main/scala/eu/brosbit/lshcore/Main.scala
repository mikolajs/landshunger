package eu.brosbit.lshcore

import eu.brosbit.hexlib.Hex
import eu.brosbit.lshcore.immovable.ImmovableObject
import eu.brosbit.lshcore.tiles.Tile

object Main:
  //
  val SIZE = 20
  val hexLib = new Hex(SIZE, SIZE)
  var days = 0
  val dayTime = 1

 
  def main(args: Array[String]): Unit =
    val game = Game(SIZE)
    //val tileMap = game.tileMap 
    //val plantsManager = game.plantsManager
    //val objectMap = plantsManager.objectMap
    val mapUnit:Array[Array[Int]] = Array.ofDim[Int](SIZE,SIZE)
    val wildAnimals = game.wildAnimals 
    val statistics = new Statistics(game.tileMap, game.objectMap)
    println(mkTileMapString(game.tileMap))
    statistics.countTiles()
    //val mapManager = MapManager(tileMap, objectMap, plantsManager, wildAnimals)
    days = 1
    val daysShow = 5
    var play = true
    while(play) do
      println(s"Dzień $days")
      game.nextDay()

//      for time <- 1 to dayTime do
//        wildAnimals.moveAnimals()

      println("STATYSTYKI:")
      statistics.countForests()
      statistics.countHPOfForest()
      statistics.countGrasses()
      statistics.countBioGrasses()
      //wildAnimals.showInfoDeer
      //wildAnimals.consumeAndGrown()
      //wildAnimals.nextDay
      println(mkMapString(game.tileMap, game.objectMap))
      Thread.sleep(3000)
      days += 1
      if days >= daysShow then play = false


  private def mkMapJson(m:Array[Array[Tile]]) =
    val mapString = m.map(line => 
      "[" + 
        line.map(t =>  "'" + t.aType.shortName + "'").mkString(",")
        + "]"
    ).mkString(",")
    "{ 'jsonMap':[" + mapString + "]}"

  private def mkMapString(m:Array[Array[Tile]], o:Array[Array[ImmovableObject]]) = 
    var lineNr = -1
    val mapString = for r <- m.indices yield 
        val firstLine = m(r).map(t => Printer.getFirstLine(t)).mkString("|")
        val secondLine = o(r).map(t => Printer.getSecondLine(t)).mkString("|")
        lineNr += 1
        if lineNr % 2 == 0 then firstLine + "\n" + secondLine
        else "  " + firstLine + "\n  " + secondLine 
        
    mapString.mkString("\n")

  private def mkTileMapString(m:Array[Array[Tile]]) = 
    var r = 1
    val mapString = m.map(line => 
        line.map(t => Printer.toViewMapSymbol(t.aType.shortName)).mkString(" ")
        ).map(line => 
          r += 1
          if r % 2 == 0 then line else " "+line
          ).mkString("\n")
    mapString
    
