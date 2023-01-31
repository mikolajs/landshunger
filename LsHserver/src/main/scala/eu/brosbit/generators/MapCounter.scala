package eu.brosbit.generators

import eu.brosbit.tiles.*
import eu.brosbit.immovable.*
import scala.util.control.Breaks.{break, breakable}


class MapCounter(map:Array[Array[Tile]]):

  val ROWS = map.size
  val COLS = map(0).size
  val allPools = ROWS*COLS
  
  def showTilesStatistics = 
    var numberOfMountains = 0
    var numberOfHills = 0
    var numberOfWaters = 0
    var numberOfFords = 0
    var numberOfSteppes = 0
    for r <- 0 until ROWS
        c <- 0 until COLS do
       if map(r)(c).aType.shortName == Mountain.shortName then numberOfMountains += 1
       else if map(r)(c).aType.shortName == Hill.shortName then numberOfHills += 1
       else if map(r)(c).aType.shortName == DeepWater.shortName then numberOfWaters += 1
       else if map(r)(c).aType.shortName == Ford.shortName then numberOfFords += 1
       else if map(r)(c).aType.shortName == Steppe.shortName then numberOfSteppes += 1
    s"""All tiles: $allPools with $numberOfMountains mountains, $numberOfHills hills, $numberOfSteppes steppes,
    | $numberOfWaters water and $numberOfFords fords""".stripMargin

  def showWoodStatistics = 
    var numberOfForests = 0;
    var amountOfWoods = 0
    for r <- 0 until ROWS
        c <- 0 until COLS do
      if (map(r)(c).aType.shortName == Mountain.shortName || map(r)(c).aType.shortName == Hill.shortName) && 
        map(r)(c).imObjOpt.isDefined && !map(r)(c).imObjOpt.get.build then
          val p = map(r)(c).imObjOpt.get.plant
          if p.obj.shortName == Forest.shortName then 
            numberOfForests += 1
            amountOfWoods += p.getHP
    s"""Forest: $numberOfForests with $amountOfWoods trees"""
    
  def showGrassStatistics = 
    var numberOfGrass = 0;
    var amountOfGrass = 0
    for r <- 0 until ROWS
        c <- 0 until COLS do
      if (map(r)(c).aType.shortName == Mountain.shortName || map(r)(c).aType.shortName == Hill.shortName || map(r)(c).aType.shortName == Steppe.shortName) && 
        map(r)(c).imObjOpt.isDefined && !map(r)(c).imObjOpt.get.build then
          val p = map(r)(c).imObjOpt.get.plant
          if p.obj.shortName == Grass.shortName then 
            numberOfGrass += 1
            amountOfGrass += p.getBio
    s"""Grass: $numberOfGrass with $amountOfGrass grass size"""  



