package eu.brosbit.lshcore

import eu.brosbit.tiles.*
import eu.brosbit.immovable.*
import eu.brosbit.lshcore.immovable.{ImmovableObject, Plant}
import eu.brosbit.lshcore.tiles.{CoolWater, DeepWater, Ford, Hill, Humus, Ice, Mountain, Plain, Sand, ShallowWater, Steppe, Swamp, Tile}
import eu.brosbit.movable.*

object Printer:
  val ANSI_RED = "\u001B[31m"
  val ANSI_GREEN = "\u001B[32m"
  val ANSI_BLUE = "\u001B[34m"
  val ANSI_WHITE = "\u001B[37m"
  val ANSI_YELLOW = "\u001B[33m"
  val ANSI_MAGENTA = "\u001B[35m"
  val ANSI_RESET = "\u001B[0m" 

  def toViewMapSymbol(short:String):String = 
     short match 
       case c if c == Plain.shortName     => "."
       case c if c == Hill.shortName      => "O" 
       case c if c == Mountain.shortName  => "X"
       case c if c == Swamp.shortName     => "v"
       case c if c == Steppe.shortName    => ","
       case c if c == Ford.shortName      => "="
       case c if c == DeepWater.shortName => "~"
       case c if c == ShallowWater.shortName => "~"
       case c if c == CoolWater.shortName => "~"
       case c if c == Ice.shortName       => "*"
       case c if c == Humus.shortName     => "#"
       case c if c == Sand.shortName      => "-"
       case c => c

  def toViewMapSymbolColor(short:String):String = 
    ANSI_MAGENTA+ toViewMapSymbol(short) + ANSI_RESET

  private def toViewMovableInfo(short: String): String =
    "  "

  private def toHexNr(nr:Int, max:Int) = 
    val x = scala.math.round(nr*15.0/max)
    if x < 10 then x.toString.head else (x + 55).toChar.toString

  private def getObjShort(imObj:ImmovableObject):String = 
    imObj match {
      case plant:Plant =>
        val name = toViewMapSymbol(plant.obj.symbol)
        val hp = toHexNr(plant.getHP, plant.obj.maxHP)
        val bio = toHexNr(plant.getBio, plant.obj.maxBio) 
        name+hp+bio
      case _ => "   " 
    }
    
    
  def getSecondLine(imObj:ImmovableObject): String =getObjShort(imObj)

  def getFirstLine(tile:Tile): String = toViewMapSymbol(tile.aType.shortName) + toViewMovableInfo("")

