package eu.brosbit.lshcore

import eu.brosbit.hexlib.Hex

class Game(SIZE:Int):
  val hexLib = new Hex(SIZE, SIZE)
  var days = 0
  var minutes = 0
  val tileMap = MapGeneratorSteppe(SIZE).getMap
  val plantsManager = new PlantsManager(tileMap)
  val objectMap = plantsManager.objectMap
  val mapUnit: Array[Array[Int]] = Array.ofDim[Int](SIZE, SIZE)
  val wildAnimals = new WildAnimals(tileMap, mapUnit, 3)
  val villageManager = VillageManager(tileMap, objectMap)
  val mapManager = MapManager(tileMap, objectMap, plantsManager, wildAnimals, villageManager)
  
  private var blocked: Boolean = false
  
  def isBlocked: Boolean = blocked

  def nextDay():Unit = {
    blocked = true
    mapManager.nextDay()
    days += 1
    minutes = 0
    blocked = false
  }
  
  def nextMinute():Unit = {
    blocked = true
    minutes += 1
    blocked = false
  }
  
  def getMapJsonStr:String =
    mapManager.getMapJson
    
  def getTilesInfo: String =
    mapManager.getTilesInfo
