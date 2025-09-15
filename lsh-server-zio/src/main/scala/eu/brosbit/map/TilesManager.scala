package eu.brosbit

import eu.brosbit.immovable.*
import eu.brosbit.tiles.*
import scala.util.Random
import eu.brosbit.hexlib.*

class TilesManager(mapWorld:Array[Array[Tile]]):
  val rows = mapWorld.size
  val cols = mapWorld(0).size 
  
  def nextDay() =  
    manageForest()
    manageCropfields()

  private def manageCropfields() = 
    ""
  // if forest is to small change to grass, if grass is full try to change to Forest
  //
  private  def manageForest() =
    val rand = Random()
    val hex = Hex(rows, cols)
    for row <- 0 until rows do
      for col <- 0 until cols do
        val tile = mapWorld(row)(col)
        if tile.imObjOpt.isDefined && !tile.imObjOpt.get.build then
          if tile.imObjOpt.get.plant.obj.shortName == Forest.shortName then
            val forest = tile.imObjOpt.get.plant
            //to small destroy and create grass
            if forest.getHP < 50 then 
              val grass = Grass()
              if forest.getBio > 10 then grass.setBio(forest.getBio) else grass.setBio(10.toByte)
              tile.imObjOpt = Some(ImmovableObject(grass))
            //is big can grow on neighbours
            if forest.getHP >= Forest.maxHP/2 && forest.getBio >= Forest.maxBio/2 then
              val mps = hex.neighbours(MapPosition(row, col))
              mps.foreach(mp => 
                val neighboursTile = mapWorld(mp.r)(mp.c)
                //if tile is grass try to change in come cases
                if neighboursTile.imObjOpt.isDefined && !neighboursTile.imObjOpt.get.build then
                  val imObj = neighboursTile.imObjOpt.get
                  if imObj.plant.obj.shortName == Grass.shortName && neighboursTile.aType.shortName != Steppe.shortName then
                    val grass = imObj.plant
                    val chance = rand.nextInt(4)
                    if chance == 2 && grass.getBio > Grass.maxBio / 2 then
                      val newForest = Forest()
                      newForest.setBio(grass.getBio*Forest.maxBio/Grass.maxBio)
                      newForest.setHP(50)
                      neighboursTile.imObjOpt = Some(ImmovableObject(newForest)) 
              )
          else if tile.imObjOpt.get.plant.obj.shortName == Grass.shortName && tile.aType.shortName != Steppe.shortName then
            val grass = tile.imObjOpt.get.plant
            val chance = rand.nextInt(10)
            //full grass can transform to forest
            if grass.getBio == Grass.maxBio &&  chance == 9 then 
              val forest = Forest()
              forest.setBio(Forest.maxBio)
              forest.setHP(50)
              tile.imObjOpt = Some(ImmovableObject(forest))


  //def isForest(obj: ImmovableObjectConst):Boolean = woods.map(_.shortName).exists(w => obj.shortName == w)


