package eu.brosbit

import eu.brosbit.tiles.*
import eu.brosbit.immovable.*
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
       
  def toViewMovableInfo(short:String) =
    "  "

  def toHexNr(nr:Int, max:Int) = 
    val x = scala.math.round(nr*15.0/max)
    if x < 10 then x.toString.head else (x + 65).toChar.toString

  private def getObjShort(imObjOpt:Option[ImmovableObject]):String = 
    imObjOpt.map(o => 
        val name = toViewMapSymbol(o.obj.shortName)
        val hp = toHexNr(o.getHP().toInt, o.obj.maxHP)
        val bio = if o.isInstanceOf[Plant] then toHexNr(o.asInstanceOf[Plant].getBio, o.asInstanceOf[Plant].obj.maxBio) else ' '
        name+hp+bio
        ).getOrElse("   ")
    
  def getSecondLine(tile:Tile) =getObjShort(tile.imObjOpt)

  def getFirstLine(tile:Tile) = toViewMapSymbol(tile.aType.shortName) + toViewMovableInfo("")

