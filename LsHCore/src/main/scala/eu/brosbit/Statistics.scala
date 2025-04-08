package eu.brosbit

import eu.brosbit.immovable.Plant
import eu.brosbit.tiles.Tile

class Statistics(map:Array[Array[Tile]]){

  def countForests: Unit = {
    val woods = map.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if(t.imObjOpt.nonEmpty && t.imObjOpt.get.obj.shortName == "F") 1 else 0)
    }))
    println(s"Number of forests: $woods")
  }

  def countHPOfForest: Unit = {
    val wood = map.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if(t.imObjOpt.nonEmpty && t.imObjOpt.get.obj.shortName == "F") t.imObjOpt.get.getHP() else 0.toShort)
    }))
    println(s"Amount of woods: $wood")
  }

  def countGrasses: Unit = {
    val grasses = map.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if(t.imObjOpt.nonEmpty && t.imObjOpt.get.obj.shortName == "G") 1 else 0)
    }))
    println(s"Number of grasses: $grasses")
  }

  def countBioGrasses: Unit = {
    val bio = map.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if(t.imObjOpt.nonEmpty && t.imObjOpt.get.obj.shortName == "G")
        t.imObjOpt.get.asInstanceOf[Plant].getBio else 0.toShort)
    }))
    println(s"Amount of Grasses: $bio")
  }
  def countTiles:Unit = 
    println("start tiles")
    val tiles = map.flatten.map(t => t.aType.name).groupBy(identity).map(e => (e._1, e._2.size))
    println("Tiles:")
    for t <- tiles do
      println(s"${t._1} : ${t._2}")


}
