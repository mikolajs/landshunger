package eu.brosbit.lshcore.immovable

import eu.brosbit.lshcore.items.ItemList
import eu.brosbit.lshcore.movable.Peasants

class Forest extends Plant:
  hp = 5
  override val obj = Forest

  override def harvest(items: ItemList, work: Int): (Int, Int) =
    var workTime = work
    val mx = (hp-5) * 3
    if workTime > mx then
      items.wood += (hp-5)
      hp = 5 
      workTime -= mx
      (hp - 5, workTime)
    else
      val hpMax = workTime / 3
      hp -= hpMax
      workTime -= hpMax*3
      items.wood += hpMax 
      (hpMax, workTime)


object Forest  extends  PlantConst  {
  def apply(): Forest = new Forest()
  override val name: String = "Forest"
  override val shortName: String = "F"
  override val symbol: String = "?"
  override val maxBio: Int = 40
  override val growBio: Int = 4
  override val growHP: Int = 20
  override val maxHP: Int = 4000
  override val maxFood: Int = 40
  override val growFood: Int = 4
}
