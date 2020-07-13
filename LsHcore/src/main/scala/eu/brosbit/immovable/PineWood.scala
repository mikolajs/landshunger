package eu.brosbit.immovable

class PineWood extends Plant {
  override val obj = PineWood
}

object PineWood  extends  PlantConst {
  def apply() = new PineWood
  override val name: String = "Pine Tree Wood"
  override val shortName: String = "PW"
  override val maxBio: Short = 80
  override val growBio: Short = 8
  override val growHP: Short = 40
  override val maxHP: Short =  800
}
