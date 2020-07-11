package eu.brosbit.immovable

class PalmWood extends Plant {
  override val obj = PineWood
  override val name: String = "Pine tree Wood"
  override val shortName: String = "PL"
}

object PalmWood extends PlantConst {
  def apply() = new PalmWood
  override val maxBio: Short = 70
  override val growBio: Short = 10
  override val growHP: Short = 60
  override val maxHP: Short = 700
}
