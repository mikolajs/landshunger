package eu.brosbit.lshcore.immovable

import eu.brosbit.lshcore.items.{ItemList, Peasants}

class Forest extends Plant:
  hp = 5
  override val obj = Forest

  override def harvest(items: ItemList, peasants: Peasants): Int =
    val mx = (hp-5) * 3
    if peasants.workTime > mx then
      items.wood += (hp-5)
      hp = 5 
      peasants.workTime -= mx
      hp - 5
    else
      val hpMax = peasants.workTime / 3
      hp -= hpMax
      peasants.workTime -= hpMax*3
      items.wood += hpMax 
      hpMax  


object Forest  extends  PlantConst  {
  def apply(): Forest = new Forest()
  override val name: String = "Forest"
  override val shortName: String = "F"
  override val symbol: String = "?"
  override val maxBio: Int = 40
  override val growBio: Int = 5
  override val growHP: Int = 20
  override val maxHP: Int = 1000
  override val maxFood: Int = 40
  override val growFood: Int = 5
}
