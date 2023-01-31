package eu.brosbit
import eu.brosbit.hexlib.MapGenerator
import zio.json.*
import eu.brosbit.hexlib.MapObjectStarterGenerator

case class TileMap(m:Array[Array[String]])
object TileMap:
  given JsonEncoder[TileMap] = DeriveJsonEncoder.gen[TileMap]

case class PlantObjectJson(n:String, bio:Int, hp:Int)
object PlantObjectJson:
  given JsonEncoder[PlantObjectJson] = DeriveJsonEncoder.gen[PlantObjectJson]

case class ImmobileObjectMap(m:Array[Array[PlantObjectJson]])
object ImmobileObjectMap:
  given JsonEncoder[ImmobileObjectMap] = DeriveJsonEncoder.gen[ImmobileObjectMap]
  

case class ErrorJson(statement:String)
object ErrorJson:
  given  JsonEncoder[ErrorJson] = DeriveJsonEncoder.gen[ErrorJson]

object MapJsonRoute:
  var aMap:Option[MapGenerator] = None
  def getTileMap = 
    aMap = Some(MapGenerator(40))
    //println(aMap.get.getMapStringJson())
    aMap.get.getMapStringForJson.toJson
   
  def getImmobileMap = 
    if aMap.isEmpty then
      ErrorJson("Map not created yet!!").toJson
    else 
      val immobileObjectGenerator = MapObjectStarterGenerator(aMap.get.worldTiles)
      immobileObjectGenerator.generate
      //println(immobileObjectGenerator.getString)
      immobileObjectConvert.toJson

  private def immobileObjectConvert = 
    aMap.get.worldTiles.map(row => row.map(t => 
      if t.imObjOpt.isEmpty then PlantObjectJson("", 0, 0)
      else 
        val obj = t.imObjOpt.get
        if obj.build then PlantObjectJson("", 0, 0) //change for buildings
        else PlantObjectJson(obj.plant.obj.shortName, obj.plant.getBio, obj.plant.getHP))
    )
