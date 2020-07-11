package eu.brosbit

import eu.brosbit.tiles.Tile
import tiles._

class TheMap(wordSize: Int) {
   private val map:Array[Array[Tile]] = Array.ofDim(wordSize, 2*wordSize)
   private val unitMap:Array[Array[Boolean]] = Array.ofDim(wordSize, 2*wordSize)

   def createMap: Unit = {
     val mapData = loadFromFileMap
     println(s"SIZE OF MAP = ${mapData.size}")
    for {i <- 0 until wordSize
        j <- 0 until 2*wordSize } {
        map(i)(j) = choiceTile(mapData(i*2*wordSize+j))
    }
  }

  def getMap: Array[Array[Tile]] = map

  def nextDay(): Unit = {
    for(a <- map; t <- a) t.nextDay
  }

  private def loadFromFileMap: Array[Byte] = {
    import java.io.File;
    import java.nio.file.Files;

    val file = new File("configdata/map.data")
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
    case 0 => new DeepWater
    case 1 => new Plain
    case 2 => new Hill
    case 3 => new Mountain
    case _ => new Plain
  }

  def printTiles: Unit = {
    for (i <- 0 until wordSize) {
      for (j <- 0 until 2 * wordSize) {
        print(map(i)(j).log + "\t")
      }
      println()
    }
  }

 private def printArray[A](arr:Array[A]): Unit = {
    for { i <- 0 until wordSize
          j <- 0 until 2*wordSize} {
        print(arr(i*2*wordSize+j)+ "\t")
        if(j == 2*wordSize -1) println()
      }

  }

}
