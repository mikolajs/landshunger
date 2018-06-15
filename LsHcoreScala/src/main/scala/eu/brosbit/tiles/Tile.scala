package eu.brosbit.tiles
import eu.brosbit.immovable._

 abstract class Tile(val tileConst:TileConstant){
  
    var imObjOpt:Option[ImmovableObject]
    def getaType:Byte = tileConst.aType
    def nextDay:Unit
    def getLev:Byte = tileConst.lev
    def getFertility:Byte = tileConst.fertility
    def setImmovableObject(o:ImmovableObject) = imObjOpt = if(imObjOpt != null) Some(o) else None
    def getName:String = tileConst.name

}


