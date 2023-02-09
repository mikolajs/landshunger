package eu.brosbit.immovable

class Forest extends Plant {
  override val obj = Forest
  
}

object Forest  extends  PlantConst  {
  def apply(): Forest = new Forest()
  override val name: String = "Forest"
  override val shortName: String = "f0"
  override val maxBio: Int = 40
  override val growBio: Int = 10
  override val growHP: Int = 50
  override val maxHP: Int = 1000

}
