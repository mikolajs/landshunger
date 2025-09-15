package eu.brosbit.immovable

class Grass extends Plant {

  override val obj = Grass
}

object Grass extends PlantConst {
  def apply(): Grass = new Grass()
  override val name: String = "grass"
  override val shortName: String = "gr"
  override val maxBio: Int = 120
  override val growBio: Int = 15
  override val growHP: Int = 0
  override val maxHP: Int = 0
}
