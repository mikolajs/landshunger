package eu.brosbit

import eu.brosbit.tiles.Tile

class Statistics(map:Array[Array[Tile]]){

  def countForests: Unit = {
    val woods = map.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if(t.imObjOpt.nonEmpty && t.imObjOpt.get.shortName == "LW") 1 else 0)
    }))
    println(s"Number of forests: $woods")
  }

  def countHPOfForest: Unit = {
    val wood = map.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if(t.imObjOpt.nonEmpty && t.imObjOpt.get.shortName == "LW") t.imObjOpt.get.getHP() else 0)
    }))
    println(s"Amount of woods: $wood")
  }

  def countGrasses: Unit = {
    val grasses = map.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if(t.imObjOpt.nonEmpty && t.imObjOpt.get.shortName == "GR") 1 else 0)
    }))
    println(s"Number of grasses: $grasses")
  }

  def countHPOGrasses: Unit = {
    val wood = map.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if(t.imObjOpt.nonEmpty && t.imObjOpt.get.shortName == "GR") t.imObjOpt.get.getHP() else 0)
    }))
    println(s"Amount of Grasses: $wood")
  }


}
