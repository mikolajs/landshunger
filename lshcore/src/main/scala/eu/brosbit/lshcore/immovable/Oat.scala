package eu.brosbit.lshcore.immovable

import eu.brosbit.lshcore.items.{ItemList, Peasants}

class Oat extends Plant:
  override val obj = Oat

  override def harvest(items: ItemList, peasants: Peasants): Int =
    if hp == 0 then 0
    else
      val wt = food * 4
      if peasants.workTime < wt then {
        items.corns += food
        items.forage += food
        food = 0
        bio = 0
        hp = 0
        peasants.workTime -= wt
        wt / 4
      } else {
        val foodGet = peasants.workTime / 4
        food -= foodGet
        items.corns += foodGet
        items.forage += foodGet
        peasants.workTime -= foodGet * 4
        foodGet
      }  

object Oat extends PlantConst:
  def apply(): Oat = new Oat()
  override val name: String = "oat"
  override val shortName: String = "O"
  override val symbol: String = "-"
  override val maxBio: Int = 80
  override val growBio: Int = 3
  override val maxFood: Int = 30;
  override val growFood: Int = 2;
  override val growHP: Int = 0
  override val maxHP: Int = 1
