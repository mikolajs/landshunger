package eu.brosbit.lshcore

import eu.brosbit.lshcore.immovable.Plant
import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec

class MainSpec extends AnyFlatSpec with Matchers {
  "It" should "always pass" in {
    assert(1 === 1)
  }

  val map = new TheMap("map.data")
  val mapArray = map.getMap
  val plantsManager = new PlantsManager(map)
  val wildAnimals = new WildAnimals(map, 3)
  val mapManager = new MapManager(map, plantsManager, wildAnimals)
  mapManager.nextDay()
//  map.printTiles
  val m = map.getMap.flatten
  "Map" should "be size of 40" in {
    map.getWordSize should be (40)
  }
  it should "has all defined fields except mountain" in {
    m.filter(_.level < 3).forall(_.imObjOpt.isDefined) should be(true)
  }
  it should "has all field with plant" in {
//    m.filter(_.level < 3).foreach(t => if(t.imObjOpt.isEmpty) println(t.name))
//    m.filter(_.level < 3).foreach(t =>
//      if(t.imObjOpt.nonEmpty && !t.imObjOpt.get.isInstanceOf[Plant]) println(t.name))
    m.filter(_.level < 3).forall(t => t.imObjOpt.get.isInstanceOf[Plant] ) should be(true)
  }

}