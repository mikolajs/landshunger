package eu.brosbit.lshcore.tiles

import eu.brosbit.lshcore.immovable.*

trait TileObj:
   val level: Byte
   val fertility: Byte
   val name: String
   val shortName: String
   def toJson: String = s"""{"n": $name, "sn":"$shortName", "l":$level}"""
end TileObj

trait Tile:
   val aType:TileObj
   

