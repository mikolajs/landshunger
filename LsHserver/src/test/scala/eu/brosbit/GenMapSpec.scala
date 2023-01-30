package eu.brosbit

import org.scalatest.matchers.should.Matchers
import hexlib.MapGenerator
import hexlib.MapObjectStarterGenerator
import org.scalatest.flatspec.AnyFlatSpec
import scala.language.postfixOps
import eu.brosbit.tiles.Plain
import eu.brosbit.tiles.Hill

class GenMapSpec extends AnyFlatSpec with Matchers:
  val sizeXY = 20
  val mapGen = MapGenerator(sizeXY)
  val objGen = MapObjectStarterGenerator(mapGen.worldTiles);
  val mapStr = mapGen.getMapStringJson();
  objGen.generate
  val objStr = objGen.getString
  println(mapStr)
  println(objStr)
  behavior of "Generate map"
  it should "Return map size 10x10" in {
    mapGen.allPools should be > 89
  }
  it should " not be empty map " in {
    val found = mapGen.getMapStringForJson.map(line => line.contains(""))
    .contains(true)
    println(mapGen.getMapStringForJson)
    found should be (false)
  }
  it should " have contains woods" in {
    val s = objStr.split(',').map(e => if e == "f0" then 1 else 0).sum
    s should be > 10
  }
  it should " never had woods on other pools then plain and hill " in {
    val tiles = mapGen.getMapStringForJson
    val objs  = objStr.split('\n').map(a => a.split(','))
    val wrong = for {
      i <- 0 until sizeXY
      j <- 0 until sizeXY
    } yield
      //println(objs(i)(j))
      if objs(i)(j) == "f0" then
        //println(s"${objs(i)(j)} ${tiles(i)(j)}")
        if tiles(i)(j) != Plain.shortName && tiles(i)(j) != Hill.shortName  then 1 else 0
      else 0
    
    wrong.sum should be (0)

  }

  
end GenMapSpec
