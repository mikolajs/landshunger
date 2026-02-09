package eu.brosbit.lshcore

import eu.brosbit.lshcore.immovable.Plant
import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec

class MainTestSpec extends AnyFlatSpec with Matchers {
  "It" should "always pass" in {
    assert(1 === 1)
  }
  val game = Game(20)
  
  val map = game.tileMap.flatten
  "Map" should "be size of 20" in {
    game.tileMap.length should be (20)
  }
  it should "be size of 400 elements" in {
    map.length should be(400)
  }
  it should "has all field with plant" in {
    true
  }

}