package eu.brosbit.lshcore.immovable

class Forest extends Plant {
  hp = 5
  override val obj = Forest
}

object Forest  extends  PlantConst  {
  def apply(): Forest = new Forest()
  override val name: String = "Forest"
  override val shortName: String = "F"
  override val symbol: String = "?"
  override val maxBio: Int = 40
  override val growBio: Int = 5
  override val growHP: Int = 20
  override val maxHP: Int = 1000
  override val maxFood: Int = 40
  override val growFood: Int = 5
}
