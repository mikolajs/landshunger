package eu.brosbit.immovable

class Plankton extends Plant {
  override val obj: PlantConst = Plankton
  override val name: String = "Plankton"
  override val shortName: String = "Pl"

}

object Plankton extends PlantConst {
  def apply() = new Plankton
  override val maxBio: Short = 300
  override val growBio: Short = 20
  override val growHP: Short = 20
  override val maxHP: Short = 300
}
