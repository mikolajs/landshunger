package eu.brosbit.lshcore

import eu.brosbit.lshcore.immovable.{Forest, Grass, ImmovableObject}
import eu.brosbit.lshcore.tiles.Tile

class Statistics(map:Array[Array[Tile]], objectMap:Array[Array[ImmovableObject]]){

  def countForests(): Unit = {
    val woods = objectMap.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if t.obj.name == Forest.name then 1 else 0)
    }))
    println(s"Number of forests: $woods")
  }

  def countHPOfForest(): Unit = {
    val wood = objectMap.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if t.obj.name == Forest.name then t.getHP else 0)
    }))
    println(s"Amount of woods: $wood")
  }

  def countGrasses(): Unit = {
    val grasses = objectMap.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if t.obj.name == Grass.name then 1 else 0)
    }))
    println(s"Number of grasses: $grasses")
  }

  def countBioGrasses(): Unit = {
    val bio = objectMap.foldLeft(0)((n,a)=> n + a.foldLeft(0)((m, t) => {
      m + (if t.obj.name == Grass.name then t.asInstanceOf[Grass].getBio else 0)
    }))
    println(s"Amount of Grasses: $bio")
  }
  def countTiles():Unit = 
    println("start tiles")
    val tiles = map.flatten.map(t => Printer.toViewMapSymbol(t.aType.shortName) + " " +t.aType.name).groupBy(identity).map(e => (e._1, e._2.length))
    println("Tiles:")
    for t <- tiles do
      println(s"${t._1} : ${t._2}")


}
