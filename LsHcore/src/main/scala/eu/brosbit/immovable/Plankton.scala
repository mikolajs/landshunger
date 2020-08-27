package eu.brosbit.immovable

class Plankton extends Plant {
  override val obj: PlantConst = Plankton
}

object Plankton extends PlantConst {
  def apply() = new Plankton
  override val name: String = "Plankton"
  override val shortName: String = "Pl"
  override val maxBio: Short = 100
  override val growBio: Short = 15
  override val growHP: Short = 0
  override val maxHP: Short = 0
}
