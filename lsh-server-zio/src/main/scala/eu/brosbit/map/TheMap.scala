package eu.brosbit

import eu.brosbit.tiles.*
import eu.brosbit.generators.*
import eu.brosbit.movable.*
import zio.Console

class TheMap(gameId:String):
  var newGameId = gameId
  var day = 0
  private var dataInfo = createMap
  private var wordSize:Int = 0
  private var worldTiles:Array[Array[Tile]] = Array.ofDim(0,0)
  startMap()
  private val mc = MapCounter(worldTiles)
  private val tileManager = TilesManager(worldTiles)
  val wildAnimals = WildAnimals(this, 10)
  private var movablePools:Array[Array[Pool]] = Array.ofDim(0, 0)
  def getMap: Array[Array[Tile]] = worldTiles
  //def getUnitMap: Array[Array[Boolean]] = unitMap
  def getWordSize:Int = wordSize
  def getTile(row:Int, col:Int) = worldTiles(row)(col)
  def getMapTilesForJson = worldTiles.map(arr => arr.map( t => t.aType.shortName)) 
  def getMovable(row:Int, col:Int) = movablePools(row)(col)

  def startMap():Unit = if !loadFromDB then createMap

  private def createMap:Unit =
    val mg = MapGenerator(50)
    wordSize = 50
    worldTiles = mg.getMap
    val mosg = MapObjectStarterGenerator(worldTiles)
    movablePools = Array.ofDim[Pool](wordSize, wordSize)

  private def loadFromDB:Boolean = false

  def nextDay() =
    day += 1
    worldTiles.foreach(line =>
      line.foreach(tile => tile.nextDay 
    ))
    tileManager.nextDay()
    println(s"Run next day in TheMap $newGameId at $day")
    showStatistics

  def showStatistics = 
    println(mc.showTilesStatistics)
    println(mc.showWoodStatistics)
    println(mc.showGrassStatistics)
    println(mc.showPlanktonStatistics)


  

  /*
  private def loadFromFileMap: Array[Byte] = {
    import java.io.File;
    import java.nio.file.Files;
    val file = new File(s"configdata/${mapPath}")
    val fileContent = Files.readAllBytes(file.toPath());
    fileContent.map(_.toInt).map(_ match {
      case x if x < 0 => 255 + x
      case x => x
    }).map(_ match {
      case x if x < 80 => 3.toByte
      case x if x < 160 => 2.toByte
      case x if x < 240 => 1.toByte
      case _ => 0.toByte
    })
  }

  private def choiceTile(tile:Byte) = tile match {
    case 0 => DeepWater()
    case 1 => Plain()
    case 2 => Hill()
    case 3 => Mountain()
    case _ => Plain()
  }
  */



end TheMap
