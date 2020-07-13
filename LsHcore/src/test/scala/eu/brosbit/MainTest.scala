

import eu.brosbit.immovable.Plant
import eu.brosbit.{MapManager, PlantsManager, TheMap}
import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec

class MainSpec extends AnyFlatSpec with Matchers {
  "It" should "always pass" in {
    assert(1 === 1)
  }

  val map = new TheMap("map.data")
  val mapArray = map.getMap
  val plantsManager = new PlantsManager(mapArray)
  val mapManager = new MapManager(map, plantsManager)
  mapManager.nextDay()
//  map.printTiles
  val m = map.getMap.flatten
  "Map" should "be size of 20" in {
    map.wordSize should be (20)
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