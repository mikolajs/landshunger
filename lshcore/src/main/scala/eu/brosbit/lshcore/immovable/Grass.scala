package eu.brosbit.lshcore.immovable

import eu.brosbit.lshcore.items.{ItemList, Peasants}

class Grass extends Plant:
  override val obj = Grass

  override def harvest(items: ItemList, peasants: Peasants): Int =
      val mx = bio * 2
      if peasants.workTime > mx then
        items.forage += bio
        bio = 0
        peasants.workTime -= mx
        bio
      else
        val bioMax = peasants.workTime / 2
        bio -= bioMax
        peasants.workTime -= bioMax*2
        items.forage += bioMax
        bioMax
   


object Grass extends PlantConst:
  def apply(): Grass = new Grass()
  override val name: String = "grass"
  override val shortName: String = "G"
  override val symbol: String = "-"
  override val maxBio: Int = 120
  override val growBio: Int = 15
  override val maxFood: Int = 0;
  override val growFood: Int = 0;
  override val growHP: Int = 0
  override val maxHP: Int = 1

