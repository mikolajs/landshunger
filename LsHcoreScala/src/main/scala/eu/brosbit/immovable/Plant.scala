package eu.brosbit.immovable

abstract  class Plant extends ImmovableObject {
  val growBio:Short
  val maxBio:Short
  val growHP: Short
  val maxHP:Short
  val name:String


  protected var bio: Short = 0
  protected var hp: Short = 0

  def getBio = bio
  def setBio(bio:Short) = this.bio = bio
  def setHP(hp:Short) = this.hp = hp
  def getHP() = hp

  override def nextDay() = yields()


  def yields() = {
    bio = (bio + growBio).toShort
    hp = (hp + growHP).toShort
    if(bio > maxBio) bio = maxBio
    if(hp > maxHP) hp = maxHP
  }


}
