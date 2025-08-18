package eu.brosbit.lshcore.immovable

import eu.brosbit.items

class SmallVillage(const:BuildingConst) extends Building:
  override val obj = VillageConst1


object VillageConst1 extends BuildingConst:
  override val name = "Small Village"
  override val maxHP = 2000
  override val maxHomes:Int = 2
  override val maxStorage:Int = 2000
  override val buildEffort:Int = 5000
  override val strongBuilding:Int = 1233 //how havy to destroy
  override val image = "village_small"
  override val stoneNeed:Int = 0
  override val woodNeed:Int = 100
  override val ironNeed:Int = 0
  
  
