package eu.brosbit.immovable

class Forest extends Plant {
  override val obj = Forest
  
}

object Forest  extends  PlantConst  {
  def apply(): Forest = new Forest()
  override val name: String = "Forest"
  override val shortName: String = "f0"
  override val maxBio: Short = 40
  override val growBio: Short = 10
  override val growHP: Short = 50
  override val maxHP: Short = 1000

}
