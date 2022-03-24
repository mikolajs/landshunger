package eu.brosbit

package eu.brosbit 

import org.scalatest.matchers.Matcher
import org.scalatest.matchers.WordSpec
import eu.brosbit.hexlib.MapGenerator
import org.scalatest.flatspec.AnyFlatSpec

class GenMapSpec extends AnyFlatSpec, Matcher:
  val  mapGen = new MapGenerator(20)
  println(mapGen.getMapJson)
  behavior of "Generate map"
  it should "Return map size 20x20" in:
    mapGen.getMapJson.size should be > 39
  
end GenMapSpec
