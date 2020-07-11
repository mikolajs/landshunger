package eu.brosbit

import eu.brosbit.immovable.LeafWood
import org.scalatest.{FlatSpec, Matchers}

class GrowPlantsSpec  extends FlatSpec with Matchers {

  val leafWood = LeafWood()

  "Leaf Wood" should "not exceed limit bio" in {
    leafWood.setBio((leafWood.maxBio - 5).toShort)
    leafWood.nextDay()
    leafWood.getBio should be (leafWood.maxBio)

  }

  it should "not exceed limit hp" in {
    leafWood.setHP((leafWood.maxHP - 5).toShort)
    leafWood.nextDay()
    leafWood.getHP should be (leafWood.maxHP)
  }

  it should "grow bio of growBio amount next Day" in {
    val start =  (leafWood.maxBio / 2.toShort).toShort
    leafWood.setBio(start)
    leafWood.nextDay()
    leafWood.getBio should be (start + leafWood.growBio)
  }
  it should "grow hp of growHP amount next Day" in {
    val start =  (leafWood.maxHP / 2).toShort
    leafWood.setHP(start)
    leafWood.nextDay()
    leafWood.getHP should be (start + leafWood.growHP)
  }

}
