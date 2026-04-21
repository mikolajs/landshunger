package eu.brosbit.lshcore.immovable

import eu.brosbit.lshcore.items.{ItemList, Peasants}

class Grass extends Plant:
  override val obj = Grass

  override def harvest(items: ItemList, peasants: Peasants): Int =
    val mx = bio * harvTime 
    if peasants.workTime > mx then
      items.forage += bio
      bio = 0
      peasants.workTime -= mx
      mx
    else
        //get only some free time of workers
        val bioGet = (peasants.workTime / 2) / harvTime
        bio -= bioGet
        peasants.workTime -= bioMax*2
        items.forage += bioMax
        bioMax
   


object Grass extends PlantConst:
  override val name: String = "grass"
  override val shortName: String = "G"
  override val symbol: String = "-"
  override val maxBio: Int = 300
  override val growBio: Int = 20 
  override val maxFood: Int = 0;
  override val growFood: Int = 0;
  override val growHP: Int = 0
  override val maxHP: Int = 1
  override val harvTime: Int = 1


