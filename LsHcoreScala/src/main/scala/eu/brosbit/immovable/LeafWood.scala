package eu.brosbit.immovable

class LeafWood extends Plant {
  override val maxBio: Short = 100
  override val growBio: Short = 10
  override val growHP: Short = 50
  override val maxHP: Short = 1000
  override val name: String = "Leaf Wood"

}

object LeafWood {
  def apply(): LeafWood = new LeafWood()
}
