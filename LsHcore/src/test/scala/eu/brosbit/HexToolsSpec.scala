package eu.brosbit

import eu.brosbit.hexlib.Hex
import eu.brosbit.movable.MapPosition
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class HexToolsSpec extends AnyFlatSpec with Matchers {
  val hex = new Hex(  20, 40)

  "Distance" should "count 1 in even row" in {
    hex.distance(MapPosition(0,0), MapPosition(0,1)) should be(1)
  }
  it should "count 1 in odd" in {
    hex.distance(MapPosition(1,1), MapPosition(1,2)) should be(1)
  }

  "Neighbours 1 hex" should "consist of six pools" in {
    hex.neighbours(MapPosition(3.toShort, 3.toShort)).length should be(6)
  }
  it should "have all points with distance of 1 pool" in {
    hex.neighbours(MapPosition(3, 3)).forall(p => hex.distance(p, MapPosition(3, 3)) == 1) should be(true)
  }

}
