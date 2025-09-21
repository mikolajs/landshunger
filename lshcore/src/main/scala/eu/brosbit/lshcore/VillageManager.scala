package eu.brosbit.lshcore

import eu.brosbit.hexlib.{Hex, MapPosition}
import eu.brosbit.lshcore.immovable.{Building, ImmovableObject, SmallVillage}
import eu.brosbit.lshcore.tiles.*

class VillageManager(tileMap:Array[Array[Tile]], objectMap:Array[Array[ImmovableObject]]):
  val SIZE: Int = tileMap.length
  var villages:List[SmallVillage] = Nil
  var occupiedPools:Array[Array[Boolean]] = Array.ofDim(SIZE, SIZE)
  countFreePools
  def setVillage(row:Int, col:Int):Boolean = {
    if !occupiedPools(row)(col) && tileMap(row)(col).aType.level > 0 && tileMap(row)(col).aType.level < 3 then {
      val village = SmallVillage(row, col)
      objectMap(row)(col) = village
      villages = village :: villages
      true
    } else false
  }
  
  private def countFreePools:Array[Array[Boolean]] = {
    val arr = Array.ofDim[Boolean](SIZE, SIZE) //occupied
    val hex = Hex(SIZE, SIZE)
    for r <- 0 until SIZE do 
      for c <- 0 until SIZE do
        val tile = tileMap(r)(c)
        if tile.aType.level < 1 && tile.aType.level > 2 then 
          arr(r)(c) = true
        val immovableObject = objectMap(r)(c)
        if immovableObject.isInstanceOf[Building] then {
          arr(r)(c) = true
          if immovableObject.obj.shortName.split(" ").last == "Villange" then {
            villages = immovableObject.asInstanceOf[SmallVillage]::villages
            val occupiedMP = hex.neighbours(MapPosition(r, c), 2)
            for mp <- occupiedMP do
              arr(r)(c) = true
          } 
        }


    arr
  }
