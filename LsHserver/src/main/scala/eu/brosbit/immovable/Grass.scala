package eu.brosbit.immovable

class Grass extends Plant {

  override val obj = Grass
}

object Grass extends PlantConst {
  def apply(): Grass = new Grass()
  override val name: String = "grass"
  override val shortName: String = "g0"
  override val maxBio: Short = 120
  override val growBio: Short = 15
  override val growHP: Short = 0
  override val maxHP: Short = 0
}
