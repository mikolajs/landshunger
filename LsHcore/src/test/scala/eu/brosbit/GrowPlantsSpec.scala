package eu.brosbit

import eu.brosbit.immovable.{Forest, Grass}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GrowPlantsSpec  extends AnyFlatSpec with Matchers {

  val leafWood = Forest()

  "Leaf Wood" should "not exceed limit bio" in {
    leafWood.setBio((leafWood.obj.maxBio - 5).toShort)
    leafWood.nextDay()
    leafWood.getBio should be (leafWood.obj.maxBio)

  }

  it should "not exceed limit hp" in {
    leafWood.setHP((leafWood.obj.maxHP - 5).toShort)
    leafWood.nextDay()
    leafWood.getHP should be (leafWood.obj.maxHP)
  }

  it should "grow bio of growBio amount next Day" in {
    val start =  (leafWood.obj.maxBio / 2.toShort).toShort
    leafWood.setBio(start)
    leafWood.nextDay()
    leafWood.getBio should be (start + leafWood.obj.growBio)
  }
  it should "grow hp of growHP amount next Day" in {
    val start =  (leafWood.obj.maxHP / 2).toShort
    leafWood.setHP(start)
    leafWood.nextDay()
    leafWood.getHP should be (start + leafWood.obj.growHP)
  }

  val grass = Grass()

  "Grass" should "setBio working" in {
    grass.setBio((Grass.maxBio - 5).toShort)
    grass.getBio should be (Grass.maxBio - 5)
  }

}
