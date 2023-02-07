package eu.brosbit
import eu.brosbit.generators.{MapGenerator, MapObjectStarterGenerator, MapCounter}
import zio.json.*
import scala.collection.immutable.*
import eu.brosbit.tiles.*

case class TileMap(id:String, m:Array[Array[String]])
object TileMap:
  given JsonEncoder[TileMap] = DeriveJsonEncoder.gen[TileMap]

case class PlantObjectJson(n:String, bio:Int, hp:Int)
object PlantObjectJson:
  given JsonEncoder[PlantObjectJson] = DeriveJsonEncoder.gen[PlantObjectJson]

case class ImmobileObjectMap(id:String, m:Array[Array[PlantObjectJson]])
object ImmobileObjectMap:
  given JsonEncoder[ImmobileObjectMap] = DeriveJsonEncoder.gen[ImmobileObjectMap]
  

case class ErrorJson(statement:String)
object ErrorJson:
  given  JsonEncoder[ErrorJson] = DeriveJsonEncoder.gen[ErrorJson]

object MapJsonRoute:
  var maps:Map[String, TheMap] = Map()

  def getTileMap(gameId:String) = 
    println("get tiles "+gameId)
    if maps.contains(gameId) then TileMap(gameId, maps(gameId).getMapTilesForJson).toJson
    else 
      val newGameId = "abc123"
      val theMap = TheMap(newGameId)
      maps = maps + (newGameId -> theMap)
      //println(aMap.etMapStringJson())
      val mc = MapCounter(theMap.getMap)
      println(mc.showTilesStatistics)
      println(mc.showWoodStatistics)
      println(mc.showGrassStatistics)
      println(mc.showPlanktonStatistics)
      TileMap(newGameId, theMap.getMapTilesForJson).toJson

   
  def getImmobileMap(gameId:String) = 
    println("get immobile "+gameId)
    if maps.contains(gameId) then 
      ImmobileObjectMap(gameId, immobileObjectConvert(maps(gameId).getMap)).toJson
    else 
      val newGameId = "abc123"
      val theMap = TheMap(newGameId)
      maps = maps + (newGameId -> theMap)
      //println(aMap.etMapStringJon())
      ImmobileObjectMap(newGameId, immobileObjectConvert(theMap.getMap)).toJson


  private def immobileObjectConvert(aMap:Array[Array[Tile]]) = 
    aMap.map(row => row.map(t => 
      if t.imObjOpt.isEmpty then PlantObjectJson("", 0, 0)
      else 
        val obj = t.imObjOpt.get
        if obj.build then PlantObjectJson("", 0, 0) //change for buildings
        else PlantObjectJson(obj.plant.obj.shortName, obj.plant.getBio, obj.plant.getHP)
      )
    )

  private def showStatistics(aMap:Array[Array[Tile]]):Unit = 
    val mc = MapCounter(aMap)
    println(mc.showTilesStatistics)
    println(mc.showWoodStatistics)
    println(mc.showGrassStatistics)
