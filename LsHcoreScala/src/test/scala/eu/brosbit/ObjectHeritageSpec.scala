package eu.brosbit

import eu.brosbit.immovable.{Grass, LeafWood}
import org.scalatest.{FlatSpec, Matchers}

class ObjectHeritageSpec extends FlatSpec with Matchers {

  val leafWood = LeafWood()
  val grass = Grass()

  "Different plants" should "have different grow for bio" in {
    leafWood.growBio shouldNot be (grass.growBio)
  }
  it should "have different grow for HP" in {
    leafWood.growHP shouldNot be(grass.growHP)
  }
  it should "have different max for bio" in {
    leafWood.maxBio shouldNot be(grass.maxBio)
  }
  it should "have different max for HP" in {
    leafWood.maxHP shouldNot be(grass.maxHP)
  }
  it should "have different names" in {
    leafWood.name shouldNot be(grass.name)
  }

}
