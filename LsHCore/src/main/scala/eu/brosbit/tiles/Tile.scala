package eu.brosbit.tiles
import eu.brosbit.immovable.*

trait TileObj:
   val level: Byte
   val fertility: Byte
   val name: String
   val shortName: String
   def getLevel:Byte = level
   def getFertility:Byte = fertility
   def getName:String = name
end TileObj

trait Tile:
   val aType:TileObj
   var imObjOpt:Option[ImmovableObject]

   def nextDay:Unit =
      if imObjOpt.nonEmpty then
         if imObjOpt.get.isInstanceOf[Plant] then
            imObjOpt.get.asInstanceOf[Plant].nextDay()


   def setImmovableObject(o:ImmovableObject) = imObjOpt = if(imObjOpt != null) Some(o) else None
   def log:String = s"${aType.shortName}:${imObjOpt.map(_.log).getOrElse("--")}\t"


