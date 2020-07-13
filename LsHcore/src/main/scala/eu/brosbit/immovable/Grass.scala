package eu.brosbit.immovable

class Grass extends Plant {

  override val obj = Grass
}

object Grass extends PlantConst {
  def apply(): Grass = new Grass()
  override val name: String = "grass"
  override val shortName: String = "GR"
  override val maxBio: Short = 400
  override val growBio: Short = 20
  override val growHP: Short = 20
  override val maxHP: Short = 400
}
