package eu.brosbit.immovable

class Grass extends Plant {
  override val maxBio: Short = 400
  override val growBio: Short = 20
  override val growHP: Short = 20
  override val maxHP: Short = 400
  override val name: String = "Grass"

}

object Grass {
  def apply(): Grass = new Grass()
}
