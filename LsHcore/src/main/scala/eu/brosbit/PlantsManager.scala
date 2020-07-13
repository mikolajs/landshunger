package eu.brosbit

import eu.brosbit.immovable.{Grass, LeafWood, PalmWood, PineWood, Plankton, Plant}
import eu.brosbit.tiles.Tile

class PlantsManager(map:Array[Array[Tile]]) {
  //private val waters = map.flatten.count(_.level == 0)
  //private val plainAndHills = map.flatten.count(t => t.level > 0 && t.level < 3)


  def nextDay(): Unit = {
    fillAllFreePools()
    setWoodInRandomFreePlace()
    growForest()
  }
// empty object fill with grass or plankton, destroyed fields replace with grass
  private def fillAllFreePools(): Unit = {
    for(a <- map; t <- a){
      if(t.imObjOpt.isEmpty && t.level > 0 && t.level < 3)  t.imObjOpt = Some(Grass())
      else if(t.imObjOpt.isEmpty && t.level == 0) t.imObjOpt = Some(Plankton())
      else if(t.imObjOpt.nonEmpty && TilesManager.isForest(t.imObjOpt.get.obj)
        && t.imObjOpt.get.asInstanceOf[Plant].getHP() < 2) t.imObjOpt = Some(Grass())
    }
  }

  private def setWoodInRandomFreePlace(): Unit = {
    val allTilesWithGrass =  map.flatten.filter(t => t.level == 1 || t.level == 2)
      .filter(_.imObjOpt.nonEmpty)
      .filter(t => t.imObjOpt.get.isInstanceOf[Grass])
      .filter(t => {
        val g = t.imObjOpt.get.asInstanceOf[Grass]
        (g.getBio.toFloat / g.obj.maxBio.toFloat) < 0.1f
      })
    val numberOfFields = (Math.random()*((allTilesWithGrass.length.toDouble*0.01).toInt + 1)).toInt
    val toSetForest = scala.util.Random.shuffle(allTilesWithGrass.indices.toList).take(numberOfFields)
    toSetForest.foreach(i => {
      val tile = allTilesWithGrass(i)

      tile.imObjOpt = Some(
        tile.aType match {
          case 10 => LeafWood()
          case 20 => PineWood()
          case 30 => PineWood()
          case _ => PalmWood()
        }
      )
    })
  }
  //create new forest near all forests
  private def growForest() : Unit = {

  }

}
