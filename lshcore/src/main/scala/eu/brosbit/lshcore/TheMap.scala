package eu.brosbit.lshcore

import java.io.File
import java.nio.file.Files
import tiles.{Tile, *}

class TheMap(mapPath:String):
  private val (wordSize:Int, map:Array[Array[Tile]]) = createMap
  private val unitMap:Array[Array[Boolean]] = Array.ofDim(wordSize, wordSize)
  def getMap: Array[Array[Tile]] = map
  def getUnitMap: Array[Array[Boolean]] = unitMap
  def getWordSize:Int = wordSize
  def getTile(row:Int, col:Int): Tile = map(row)(col)

  private def createMap =
    val mapData = loadFromFileMap
    val wordSize = calculateWordSize(mapData.length)
    val map:Array[Array[Tile]] = Array.ofDim(wordSize, wordSize)
    println(s"SIZE OF MAP = ${mapData.length}")
    println(s"Size of World: $wordSize")
    for {i <- 0 until wordSize
         j <- 0 until wordSize } do
      map(i)(j) = choiceTile(mapData(i*wordSize+j))
    (wordSize, map)


  private def loadFromFileMap: Array[Byte] =
    val file = new File(s"configdata/$mapPath")
    val fileContent = Files.readAllBytes(file.toPath)
    fileContent.map(_.toInt).map {
      case x if x < 0 => 255 + x
      case x => x
    }.map {
      case x if x < 80 => 3.toByte
      case x if x < 160 => 2.toByte
      case x if x < 240 => 1.toByte
      case _ => 0.toByte
    }

  private def choiceTile(tile:Byte) = tile match {
    case 0 => new DeepWater
    case 1 => new Plain
    case 2 => new Hill
    case 3 => new Mountain
    case _ => new Plain
  }

  private def calculateWordSize(l:Int) = Math.round(Math.sqrt(l)).toInt

  def printTiles(): Unit =
    for i <- 0 until wordSize do
      for j <- 0 until wordSize do
        print(map(i)(j).aType.shortName + "\t")
      println()


  private def printArray[A](arr:Array[A]): Unit =
    for { i <- 0 until wordSize
          j <- 0 until wordSize} do
      print(arr(i*wordSize+j).toString + "\t")
      if(j == wordSize -1) println()



