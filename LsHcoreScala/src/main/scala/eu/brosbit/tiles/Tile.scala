package eu.brosbit.tiles
import eu.brosbit.immovable._

 trait Tile {
    val aType:Byte
    val level: Byte
    val fertility: Byte
    val name: String

    var imObjOpt:Option[ImmovableObject]

    def getType:Byte = aType
    def nextDay:Unit
    def getLevel:Byte = level
    def getFertility:Byte = fertility
    def setImmovableObject(o:ImmovableObject) = imObjOpt = if(imObjOpt != null) Some(o) else None
    def getName:String = name

}


