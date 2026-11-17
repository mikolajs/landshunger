package eu.brosbit.lshcore.immovable

import eu.brosbit.lshcore.items.ItemList
import eu.brosbit.lshcore.movable.Peasants

class Clover extends Plant:
  override val obj = Clover

  override def harvest(items: ItemList, work: Int): (Int, Int) =
    var workTime = work
    val mx = bio * 2
    if workTime > mx then
      items.forage += bio
      bio = 0
      workTime -= mx
      (bio, workTime)
    else
      val bioMax = workTime / 2
      bio -= bioMax
      workTime -= bioMax*2
      items.forage += bioMax
      (bioMax, workTime)
   


object Clover extends PlantConst:
  def apply(): Clover = new Clover()
  override val name: String = "clover"
  override val shortName: String = "C"
  override val symbol: String = "-"
  override val maxBio: Int = 850
  override val growBio: Int = 30 
  override val maxFood: Int = 0;
  override val growFood: Int = 0;
  override val growHP: Int = 0
  override val maxHP: Int = 1

