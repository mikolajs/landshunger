package eu.brosbit.lshcore.immovable

import eu.brosbit.lshcore.items.{ItemList, Peasants}

class Plankton extends Plant {
  override val obj: PlantConst = Plankton
  override def harvest(items: ItemList, peasants: Peasants): Int = 0
}

object Plankton extends PlantConst {
  def apply() = new Plankton
  override val name: String = "Plankton"
  override val shortName: String = "Pl"
  override val symbol: String = ":"
  override val maxBio: Int = 100
  override val growBio: Int = 15
  override val growHP: Int = 0
  override val maxHP: Int = 0
  override val maxFood = 0
  override val growFood: Int = 0
}
