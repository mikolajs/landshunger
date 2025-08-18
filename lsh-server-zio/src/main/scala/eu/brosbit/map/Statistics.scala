package eu.brosbit

import eu.brosbit.immovable.Plant
import eu.brosbit.tiles.Tile

class Statistics(map:TheMap):
  val n = "TODO: Implement"
  /*
  def countForests: Unit = 
    val woods = map.getMap.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if(t.imObjOpt.nonEmpty && t.imObjOpt.get.obj.shortName == "F") 1 else 0)
    }))
    println(s"Number of forests: $woods")
  

  def countHPOfForest: Unit = 
    val wood = map.getMap.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if(t.imObjOpt.nonEmpty && t.imObjOpt.get.obj.shortName == "F") t.imObjOpt.get.getHP() else 0)
    }))
    println(s"Amount of woods: $wood")

  def countGrasses: Unit = 
    val grasses = map.getMap.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if(t.imObjOpt.nonEmpty && t.imObjOpt.get.obj.shortName == "G") 1 else 0)
    }))
    println(s"Number of grasses: $grasses")

  def countBioGrasses: Unit = 
    val bio = map.getMap.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if(t.imObjOpt.nonEmpty && t.imObjOpt.get.obj.shortName == "G")
        t.imObjOpt.get.asInstanceOf[Plant].getBio else 0)
    }))
    println(s"Amount of Grasses: $bio")
 */

end Statistics
