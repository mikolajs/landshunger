package eu.brosbit.lshcore

import eu.brosbit.lshcore.immovable.{Grass, Plant}
import eu.brosbit.lshcore.movable.Deer
import eu.brosbit.lshcore.tiles.{Plain, Tile}
import eu.brosbit.movable.{MapPosition}
import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers

class WildAnimalsSpec extends AnyFlatSpecLike with Matchers {


  val mapTestPath = "test.data"
  val map = new TheMap(mapTestPath)
  val flattenMapArray = map.getMap.flatten
  flattenMapArray.foreach(_.imObjOpt = Some(Grass()))
  flattenMapArray.foreach(_.imObjOpt.get.asInstanceOf[Grass].setBio(20))

  val units:Array[Array[Boolean]] = Array.ofDim(5,10)

  val wildAnimals = new WildAnimals(map, 2)
  val heard = new Deer(2, 3)
  wildAnimals.addDeerHeard(heard)

  "Deer" should "be one heard of Deer" in {
    wildAnimals.getDeer.length should be(1)
  }

  it should "go to 1, 3 position as destination" in {
    map.getTile(1, 3).imObjOpt.get.asInstanceOf[Grass].setBio((Grass.maxBio - 5).toShort)
    wildAnimals.findNewDestinationSimpleStrategy
    heard.moveTo.head should be(MapPosition(1, 3))
  }

  def printlnGrass(): Unit ={
      for(a <-  map.getMap) {
        for(t <- a){
          print(t.imObjOpt.get.asInstanceOf[Plant].getBio + "\t")
        }
        println()
      }

  }
}
