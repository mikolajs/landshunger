package eu.brosbit.lshcore.immovable

import eu.brosbit.lshcore.items.{ItemList, Peasants}

abstract  class Plant extends ImmovableObject {

  val obj:PlantConst

  protected var bio: Int = 1
  protected var food: Int = 0
  protected var hp: Int = 0

  def getBio: Int = bio
  def setBio(bio:Int): Unit = this.bio = bio
  def setHP(hp:Int): Unit = this.hp = hp
  def getHP: Int = hp
  def getFood: Int = food

  override def nextDay(): Unit = yields()

  override def log: String = {
    s"${obj.shortName}:hp=${hp},bi=$bio,fo=$food"
  }
  
  def harvest(items:ItemList, peasants: Peasants):Int
  
  private def yields(): Unit = {
    bio = bio + obj.growBio
    hp = hp + obj.growHP
    food = food + obj.growFood
    if(bio > obj.maxBio) bio = obj.maxBio
    if(hp > obj.maxHP) hp = obj.maxHP
    if food > obj.maxFood then food = obj.maxFood
    if obj.growHP == 0 && bio == obj.maxBio && food == obj.maxFood then hp = 1
  }

  override def toJson: String =
    s""" {"type":"${obj.aType}", "shortName":"${obj.shortName}", "bio":"$bio", "food":"$food",
       | "hp":"$hp" }
       |""".stripMargin

}

trait PlantConst extends ImmovableObjectConst {
  override val aType = "P"
  val name:String = "Plant"
  val shortName:String ="pl"
  val symbol = " "
  val growBio:Int = 0
  val maxBio:Int = 0
  val growHP:Int = 0
  val maxHP:Int  = 0
  val maxFood:Int = 0
  val growFood:Int = 0
}
