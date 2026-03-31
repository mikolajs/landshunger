package eu.brosbit.lshcore.immovable

import eu.brosbit.lshcore.items.{ItemList, Peasants}

class Rye extends Plant:
  override val obj = Rye

  override def harvest(items: ItemList, peasants: Peasants): Int =
    if hp == 0 then 0
    else
      val wt = food * 3
      if peasants.workTime > wt then {
        items.corns += food
        food = 0
        bio = 0
        hp = 0
        peasants.workTime -= wt
        wt / 4
      } else {
        val foodGet = peasants.workTime / 3
        food -= foodGet
        items.corns += foodGet
        peasants.workTime -= foodGet * 4
        foodGet
      }

object Rye extends PlantConst:
  override val name: String = "rye"
  override val shortName: String = "R"
  override val symbol: String = "-"
  override val maxBio: Int = 160
  override val growBio: Int = 6
  override val maxFood: Int = 160;
  override val growFood: Int = 6;
  override val growHP: Int = 0
  override val maxHP: Int = 1
