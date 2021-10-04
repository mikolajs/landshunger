package eu.brosbit

import eu.brosbit.immovable.{Grass, Forest}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class ObjectHeritageSpec extends AnyFlatSpec with Matchers {

  val leafWood = Forest()
  val grass = Grass()

  "Different plants" should "have different grow for bio" in {
    leafWood.obj.growBio shouldNot be (grass.obj.growBio)
  }
  it should "have different grow for HP" in {
    leafWood.obj.growHP shouldNot be(grass.obj.growHP)
  }
  it should "have different max for bio" in {
    leafWood.obj.maxBio shouldNot be(grass.obj.maxBio)
  }
  it should "have different max for HP" in {
    leafWood.obj.maxHP shouldNot be(grass.obj.maxHP)
  }
  it should "have different names" in {
    leafWood.obj.name shouldNot be(grass.obj.name)
  }

}
