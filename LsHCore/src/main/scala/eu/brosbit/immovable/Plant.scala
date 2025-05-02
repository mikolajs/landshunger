package eu.brosbit.immovable

abstract  class Plant extends ImmovableObject {

  val obj:PlantConst

  protected var bio: Short = 1
  protected var food: Short = 0
  protected var hp: Short = 0

  def getBio = bio
  def setBio(bio:Short) = this.bio = bio
  def setHP(hp:Short) = this.hp = hp
  def getHP() = hp
  def getFood() = food

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

  override def jsonStr: String =
    s""" {"type":"${obj.genType}", ${obj.shortName}":"${obj.shortName}", "bio":"$bio", "food":"$food",
       | "hp":"$hp" }
       |""".stripMargin

}

trait PlantConst extends ImmovableObjectConst {
  val genType = "P"
  val name:String = "Plant"
  val shortName:String ="pl"
  val symbol = " "
  val growBio:Short = 0
  val maxBio:Short = 0
  val growHP: Short = 0
  val maxHP:Short  = 0
  val maxFood:Short = 0
  val growFood:Short = 0
}
