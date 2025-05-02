package eu.brosbit.immovable

class Forest extends Plant {
  hp = 5
  override val obj = Forest

}

object Forest  extends  PlantConst  {
  def apply(): Forest = new Forest()
  override val name: String = "Forest"
  override val shortName: String = "F"
  override val symbol: String = "?"
  override val maxBio: Short = 40
  override val growBio: Short = 5
  override val growHP: Short = 20
  override val maxHP: Short = 1000
  override val maxFood: Short = 40
  override val growFood: Short = 5
}
