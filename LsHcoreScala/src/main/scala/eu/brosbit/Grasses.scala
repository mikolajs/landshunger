package eu.brosbit

import eu.brosbit.immovable.Grass
import eu.brosbit.tiles.Tile

class Grasses(map:Array[Array[Tile]]) {

  def nextDay() = {
    fillAllFreePools()
  }

  def fillAllFreePools(): Unit = {
    for(a <- map; t <- a){
      if(t.imObjOpt.isEmpty && t.aType < 30 && t.aType > 9)  t.imObjOpt = Some(new Grass)
    }
  }

}
