package eu.brosbit.lshcore
import  eu.brosbit.lshcore.tiles.*

object LoadGame:
  val mapPath = "mainmap.txt"

  def loadMap = 
    val lines = scala.io.Source.fromFile(mapPath).getLines
      .map(line => line.split(" "))
    val SIZE = lines.size
    lines.map(line => line.map( s =>
          s match 
          case n if n == Plain.shortName => Plain()
          case n if n == Humus.shortName => Humus()
          case n if n == Hill.shortName => Hill()
          case n if n == Mountain.shortName => Mountain()
          case n if n == Ford.shortName => Ford()
          case n if n == ShallowWater.shortName => ShallowWater()
          case n if n == DeepWater.shortName => DeepWater()
          case n if n == CoolWater.shortName => CoolWater()
          case n if n == Steppe.shortName => Steppe()
          case n if n == Sand.shortName => Sand()
          case n if n == Swamp.shortName => Swamp()
          case _ => Ice()
      ).toArray
    ).toArray

  def saveMap(arr:Array[Array[Tile]]) =
    val txt = arr.map(_.mkString(" ")).mkString("\n")
    java.nio.file.Files.write(java.nio.file.Paths.get(mapPath), txt.getBytes)

