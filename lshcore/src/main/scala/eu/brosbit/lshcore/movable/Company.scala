package eu.brosbit.lshcore.movable

// horses: workhorse, destrier, bachmat 
// weapons: rapier, sword, broadsword, sabre, axe, halberd, warhammer
// lances: lance, longlance, shortlance, pike, 
// shoots: arch, javelin, pistols, arkebus, musket, 
// armor: cuiras, mail, kolet, collar, fullarmor
trait Company:
  var size:Int = 120
  var ammo = 12
  var lance = 0
  var spirit = 10
  var expGreen = 120
  var expMedium = 0
  var expVeteran = 0
  var horseEat = 0
  val obj:CompanyConst

trait CompanyConst:
  val maxSize = 120
  val maxAmmo = 12
  val loadTime = 10
  val shootDistance = 100
  val firstWeaponType = "rapier"
  val secondWeaponType = "no"
  val ammoType = "musket"
  val lanceType = "no"
  val maxSpirit = 100
  val meleeSoft = 20
  val meleeHard = 10
  val horse = "no"
  val pancertype = ""
  

