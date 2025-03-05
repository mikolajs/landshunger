package eu.brosbit

import eu.brosbit.tiles.*
import eu.brosbit.immovable.*
import eu.brosbit.movable.*

object Printer:

  def toViewMapSymbol(short:String):String = 
     short match 
       case c if c == "pl" => "."
       case c if c == "hi" => "O"
       case c if c == "mo" => "X"
       case c if c == "sw" => "v"
       case c if c == "st" => ","
       case c if c == "fd" => "="
       case c if c == "wd" => "~"
       case c if c == "ws" => "~"
       case c if c == "wc" => "~"
       case c if c == "ic" => "*"
       case c if c == "hu" => "#"
       case c => c

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

