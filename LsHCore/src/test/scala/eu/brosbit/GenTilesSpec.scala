package eu.brosbit

import org.scalatest.matchers.should.Matchers
import eu.brosbit.MapGeneratorSteppe
import org.scalatest.flatspec.AnyFlatSpec
import scala.language.postfixOps

class GenMapSpec extends AnyFlatSpec with Matchers:
  val  mapGen = MapGeneratorSteppe(30)
  println(mapGen.getMapJson())
  behavior of "Generate map"
  it should "Return map size 20x20" in {
    mapGen.getMapJson().size should be > 89
  }

end GenMapSpec
