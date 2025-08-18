package eu.brosbit.immovable

class Plankton extends Plant {
  override val obj: PlantConst = Plankton
}

object Plankton extends PlantConst {
  def apply() = new Plankton
  override val name: String = "Plankton"
  override val shortName: String = "pk"
  override val maxBio: Int = 100
  override val growBio: Int = 15
  override val growHP: Int = 0
  override val maxHP: Int = 0
}
