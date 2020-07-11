package eu.brosbit.tiles
import eu.brosbit.immovable._

 trait Tile {
    val aType:Byte
    val level: Byte
    val fertility: Byte
    val name: String
    val shortName: String

    var imObjOpt:Option[ImmovableObject]

    def getType:Byte = aType
    def nextDay:Unit = {
       if(imObjOpt.nonEmpty){
          if(imObjOpt.get.isInstanceOf[Plant]) {
             imObjOpt.get.asInstanceOf[Plant].nextDay()
          }
       }
    }
    def getLevel:Byte = level
    def getFertility:Byte = fertility
    def setImmovableObject(o:ImmovableObject) = imObjOpt = if(imObjOpt != null) Some(o) else None
    def getName:String = name
    def log:String = {
       s"$shortName:${imObjOpt.map(_.log).getOrElse("--")}\t"
    }

}


