package eu.brosbit

import eu.brosbit.immovable.{Grass, LeafWood, PineWood}
import eu.brosbit.tiles.Tile

class Woods(map:Array[Array[Tile]]) {
  var number = 0
  def nexDay() = {
    setWoodInRandomFreePlace()
    growForest()
  }


  private def setWoodInRandomFreePlace(): Unit = {
      val allTilesWithGrass =  map.flatten.filter(t => t.level == 1 || t.level == 2)
        .filter(_.imObjOpt.nonEmpty)
        .filter(t => t.imObjOpt.get.isInstanceOf[Grass])
        .filter(t => {
          val g = t.imObjOpt.get.asInstanceOf[Grass]
          (g.getBio.toFloat / g.obj.maxBio.toFloat) < 0.1f
        })
      val n = scala.math.ceil(allTilesWithGrass.size.toDouble*0.02).toInt
        val toSetForest = scala.util.Random.shuffle((0 until allTilesWithGrass.size).toList).take(n)
      toSetForest.foreach(i => {
        val tile = allTilesWithGrass(i)
        tile.imObjOpt = Some(
          tile.aType match {
          case 10 => LeafWood()
          case 20 => PineWood()
          }
        )
      })
  }
  //create new forest near all forests
  private def growForest() : Unit = {

  }

}
