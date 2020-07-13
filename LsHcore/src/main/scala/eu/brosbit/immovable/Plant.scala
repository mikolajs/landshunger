package eu.brosbit.immovable

abstract  class Plant extends ImmovableObject {

  val obj:PlantConst

  protected var bio: Short = 0
  protected var hp: Short = 0

  def getBio = bio
  def setBio(bio:Short) = this.bio = bio
  def setHP(hp:Short) = this.hp = hp
  def getHP() = hp

  override def nextDay() = yields()

  override def log: String = {
    s"${obj.shortName}:hp=${hp},bi=$bio"
  }

  def yields() = {
    bio = (bio + obj.growBio).toShort
    hp = (hp + obj.growHP).toShort
    if(bio > obj.maxBio) bio = obj.maxBio
    if(hp > obj.maxHP) hp = obj.maxHP
  }

}

trait PlantConst extends ImmovableObjectConst {
  val name:String = "Plant"
  val shortName:String ="pl"
  val growBio:Short = 0
  val maxBio:Short = 0
  val growHP: Short = 0
  val maxHP:Short  = 0
}
