package eu.brosbit

import eu.brosbit.HexFlat
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class HexToolsFlatSpec extends AnyFlatSpec with Matchers {
  val hex = new HexFlat(5, 5)


  "Distance for flat" should "count 1 from 0,0 to 0,1" in {
    hex.distance(MapPosition(0,0), MapPosition(0,1)) should be(1)
  }
  it should "count 1  from 0,1 to 0,0" in {
    hex.distance(MapPosition(0,1), MapPosition(0,0)) should be(1)
  }
  it should "count 4 from 0,0 to 0,4" in {
    hex.distance(MapPosition(0,0), MapPosition(0,4)) should be(4)
  }
  it should "count 4 from 0,0 to 1,4" in {
    hex.distance(MapPosition(0,0), MapPosition(1,4)) should be(5)
  }
  it should "count 6 from 0,0 to 4,4" in {
    hex.distance(MapPosition(0,0), MapPosition(4,4)) should be(6)
  }
  it should "count 2 from 0,1 to 1,2" in {
    hex.distance(MapPosition(0,1), MapPosition(1,2)) should be(2)
  }
  it should "count 2 from 1,2 to 2,4" in {
    hex.distance(MapPosition(1,2), MapPosition(2,4)) should be(2)
  }
  it should "count 2  from 2,1 to 3,4" in {
    hex.distance(MapPosition(2,1), MapPosition(3,4)) should be(4)
  }
  it should "count 3 from 1,1 to 2,4" in {
    hex.distance(MapPosition(1,1), MapPosition(2,4)) should be(3)
  }
  it should "count 3 from 1,1 to 4,3" in {
    hex.distance(MapPosition(1,1), MapPosition(4,3)) should be(3)
  }

  " Neighbours of position" should "have 18 pools for 2,2 of 2 distance neighbours" in {
    hex.neighbours(MapPosition(2, 2), 2).length should be(18)
  }
  it should "have 6 pools for 2,2 of 1 distance neighbours" in {
    hex.neighbours(MapPosition(2, 2)).length should be(6)
  }
  it should "have for 2,2 six neighbours in list" in {
    val l = hex.neighbours(MapPosition(2,2))
      .sortWith((mp1, mp2) => if(mp1.r == mp2.r) mp1.c < mp2.c else mp1.r < mp2.r)
    val expect = List(
      MapPosition(1,1), MapPosition(1,2),MapPosition(2,1), MapPosition(2,3), MapPosition(3,1), MapPosition(3,2)
    )
    l.length should be (expect.length)
    l.zip(expect).forall(e => e._1 == e._2) should be (true)
  }
  it should "have for 3,2 six neighbours in list" in {
    val l = hex.neighbours(MapPosition(3,2))
      .sortWith((mp1, mp2) => if(mp1.r == mp2.r) mp1.c < mp2.c else mp1.r < mp2.r)
    val expect = List(
       MapPosition(2,2), MapPosition(2,3), MapPosition(3,1), MapPosition(3,3), MapPosition(4,2), MapPosition(4,3)
    )
    l.length should be (expect.length)
    l.zip(expect).forall(e => e._1 == e._2) should be (true)
  }

  it should "have for 2,2 18 neighbours in list" in {
    val l = hex.neighbours(MapPosition(2,2), 2)
      .sortWith((mp1, mp2) => if(mp1.r == mp2.r) mp1.c < mp2.c else mp1.r < mp2.r)
    val expect = List(
      MapPosition(0,1), MapPosition(0,2), MapPosition(0,3),
      MapPosition(1,0), MapPosition(1,1), MapPosition(1,2), MapPosition(1,3),
      MapPosition(2,0), MapPosition(2,1), MapPosition(2,3), MapPosition(2,4),
      MapPosition(3,0), MapPosition(3,1), MapPosition(3,2), MapPosition(3,3),
      MapPosition(4,1), MapPosition(4,2), MapPosition(4,3)
    )
    l.length should be (expect.length)
    l.zip(expect).forall(e => e._1 == e._2) should be (true)
  }
  it should "have for 3,2 15 neighbours in list" in {
    val l = hex.neighbours(MapPosition(3, 2), 2)
      .sortWith((mp1, mp2) => if(mp1.r == mp2.r) mp1.c < mp2.c else mp1.r < mp2.r)
    val expect = List(
      MapPosition(1,1), MapPosition(1,2), MapPosition(1,3),
      MapPosition(2,1), MapPosition(2,2), MapPosition(2,3), MapPosition(2,4),
      MapPosition(3,0), MapPosition(3,1), MapPosition(3,3), MapPosition(3,4),
      MapPosition(4,1), MapPosition(4,2), MapPosition(4,3), MapPosition(4,4)
    )
    l.length should be (expect.length)
    l.zip(expect).forall(e => e._1 == e._2) should be (true)
  }
  it should "have for 0,1 9 neighbours in list" in {
    val l = hex.neighbours(MapPosition(0, 1), 2)
      .sortWith((mp1, mp2) => if(mp1.r == mp2.r) mp1.c < mp2.c else mp1.r < mp2.r)
    val expect = List(
      MapPosition(0,0), MapPosition(0,2), MapPosition(0,3), 
      MapPosition(1,0), MapPosition(1,1), MapPosition(1,2),
      MapPosition(2,0), MapPosition(2,1), MapPosition(2,2)
    )
    l.length should be (expect.length)
    l.zip(expect).forall(e => e._1 == e._2) should be (true)
  }
  it should "have for 3,4 8 neighbours in list" in {
    val l = hex.neighbours(MapPosition(3, 4), 2)
      .sortWith((mp1, mp2) => if(mp1.r == mp2.r) mp1.c < mp2.c else mp1.r < mp2.r)
    val expect = List(
      MapPosition(1,3), MapPosition(1,4), MapPosition(2,3), MapPosition(2,4),
      MapPosition(3,2), MapPosition(3,3), MapPosition(4,3), MapPosition(4,4)
    )
    l.length should be (expect.length)
    l.zip(expect).forall(e => e._1 == e._2) should be (true)
  }
//  it should "have all points with distance of 1 pool" in {
//    hex.neighboursRounded(MapPosition(3, 3)).forall(p => hex.distance(p, MapPosition(3, 3)) == 1) should be(true)
//  }

}
