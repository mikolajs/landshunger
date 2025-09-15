package eu.brosbit.immovable

abstract  class Plant {

  val obj:PlantConst

  protected var bio: Int = 0
  protected var hp: Int = 0

  def getBio = bio
  def setBio(bio:Int) = this.bio = bio
  def setHP(hp:Int) = this.hp = hp
  def getHP = hp

  def nextDay() = yields()

  def log: String = {
    s"${obj.shortName}:hp=${hp},bi=$bio"
  }

  def yields() = {
    bio = (bio + obj.growBio)
    hp = (hp + obj.growHP)
    if(bio > obj.maxBio) bio = obj.maxBio
    if(hp > obj.maxHP) hp = obj.maxHP
  }

}

trait PlantConst {
  val name:String = "Plant"
  val shortName:String ="pl"
  val growBio:Int = 0
  val maxBio:Int = 0
  val growHP:Int = 0
  val maxHP:Int = 0
}
