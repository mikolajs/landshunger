package eu.brosbit.lshcore

import eu.brosbit.lshcore.immovable.ImmovableObject
import eu.brosbit.lshcore.tiles.*

class MapManager(tileMap: Array[Array[Tile]], objectMap: Array[Array[ImmovableObject]], 
                 plantsManager: PlantsManager, wildAnimals: WildAnimals, villageManager: VillageManager) {

  //def createDeers(n:Int):Unit = (1 to n).foreach(_ => wildAnimals.createDeerHeard)

  def nextDay(): Unit = {
    plantsManager.nextDay()
    //wildAnimals.calculateForage()
  }

  def getMapJson: String = {
    val map = tileMap
      .map(line => "[" + line.map(tile => "\"" + tile.aType.shortName + "\"").mkString(", ") + "]\n")
      .mkString(", ")
    s"""{"map": [$map]}"""
  }

  def getObjectJson: String = {
    objectMap
      .map(line => "[" + line.map(obj => obj.toJson).mkString(",") + "]")
    s"""{"obj":}"""
  }

  def getTilesInfo: String = {
    val list = List(CoolWater.toJson, DeepWater.toJson, Ford.toJson, Hill.toJson, Humus.toJson, Ice.toJson,
      Mountain.toJson, Plain.toJson, Sand.toJson, ShallowWater.toJson, Steppe.toJson, Swamp.toJson)
    s"""{"map":[${list.mkString(",")}}]}"""
  }
  ///TODO
  def getImmovableInfo:String = {
    val list = List()
    s"""{"obj":[${list.mkString(",")}]}"""
  }
}