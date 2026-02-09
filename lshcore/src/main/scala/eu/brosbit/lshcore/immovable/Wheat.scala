package eu.brosbit.lshcore.immovable

import eu.brosbit.lshcore.items.{ItemList, Peasants}

class Wheat extends Plant:
  override val obj = Wheat

  override def harvest(items: ItemList, peasants: Peasants): Int =
    if hp == 0 then 0
    else if food <= 0 then {
      food = 0
      hp = 0
      0
    } else
      val wt = food*3
      if peasants.workTime < wt then {
        items.corns += food
        food = 0
        bio = 0
        hp = 0
        peasants.workTime -= wt
        wt/3
      } else { 
        val foodGet = peasants.workTime/3
        food -= foodGet
        peasants.workTime -= foodGet*3 
        items.corns += foodGet
        foodGet
      }


object Wheat extends PlantConst:
  def apply(): Wheat = new Wheat()
  override val name: String = "wheat"
  override val shortName: String = "H"
  override val symbol: String = "-"
  override val maxBio: Int = 90
  override val growBio: Int = 4
  override val maxFood: Int = 70;
  override val growFood: Int = 3;
  override val growHP: Int = 0
  override val maxHP: Int = 1
