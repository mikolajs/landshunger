package eu.brosbit.immovable

class LeafWood extends Plant {
  override val obj = LeafWood

}

object LeafWood  extends  PlantConst  {
  def apply(): LeafWood = new LeafWood()
  override val name: String = "Leaf Wood"
  override val shortName: String = "LW"
  override val maxBio: Short = 100
  override val growBio: Short = 10
  override val growHP: Short = 50
  override val maxHP: Short = 1000

}
