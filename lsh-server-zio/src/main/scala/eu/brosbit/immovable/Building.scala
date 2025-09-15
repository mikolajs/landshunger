package eu.brosbit.immovable

abstract class Building():
  val obj:BuildingConst

trait BuildingConst:
  val name:String
  val shortName:String
  val hp:Int
