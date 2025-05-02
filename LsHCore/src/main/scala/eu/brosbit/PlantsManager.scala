package eu.brosbit
import eu.brosbit.hexlib.*
import eu.brosbit.immovable.{Forest, Grass, Plankton, Plant}
import eu.brosbit.tiles.Tile

class PlantsManager(map:Array[Array[Tile]]) {
  private val waters = map.flatten.count(_.aType.level == 0)
  private val plainAndHills = map.flatten.count(t => t.aType.level > 0 && t.aType.level < 3)
  private val hexlib = Hex(map.size, map.head.size)
  createBeginWoods()

  def nextDay(): Unit = {
    fillAllFreePools()
    setWoodInRandomFreePlace()
    growForest()
  }
// empty object fill with grass or plankton, destroyed fields replace with grass
  private def fillAllFreePools(): Unit = {
    for(a <- map; t <- a){
      if(t.imObjOpt.isEmpty && t.aType.level > 0 && t.aType.level < 3)  t.imObjOpt = Some(Grass())
      else if(t.imObjOpt.isEmpty && t.aType.level == 0) t.imObjOpt = Some(Plankton())
      else if(t.imObjOpt.nonEmpty && TilesManager.isForest(t.imObjOpt.get.obj)
        && t.imObjOpt.get.asInstanceOf[Plant].getHP() < 2) t.imObjOpt = Some(Grass())
    }
  }

  //create new random wood 
  private def setWoodInRandomFreePlace(): Unit = {
    val allTilesWithGrass = map.flatten.filter(t => t.aType.level > 0 && t.aType.level < 3) 
      .filter(_.imObjOpt.nonEmpty)
      .filter(t => t.imObjOpt.get.isInstanceOf[Grass])
      .filter(t => {
        val g = t.imObjOpt.get.asInstanceOf[Grass]
        (g.getBio.toFloat / g.obj.maxBio.toFloat) < 0.1f && Math.random() > 0.2
      })
    val numberOfFields = (Math.random()*((allTilesWithGrass.length.toDouble*0.01).toInt + 1)).toInt
    val toSetForest = scala.util.Random.shuffle(allTilesWithGrass.indices.toList).take(numberOfFields)
    toSetForest.foreach(i => {
      val tile = allTilesWithGrass(i)
      tile.imObjOpt = Some(Forest())

    })
  }
  private def createBeginWoods():Unit = 
    for a <- map
        t <- a do
          if (t.aType.shortName == "hi" || t.aType.shortName == "pl") && Math.random() < 0.2f then
            t.imObjOpt = Some(Forest())
          
  //create new forest near all forests - sieve woods
  private def growForest() : Unit = 
    for r <- 0 until map.size
      c <- 0 until map(0).size do
        val tImObj = map(r)(c).imObjOpt
        if tImObj.nonEmpty && TilesManager.isForest(tImObj.get.obj) && Math.random() > 0.7 then
          val posible = hexlib.neighbours(MapPosition(r, c)).filter(mp => 
             val tImObj = map(mp.r)(mp.c).imObjOpt
             tImObj.nonEmpty && tImObj.get.obj.shortName == "G"
          )
          if posible.size > 0 then 
            val mp = posible((Math.random()*posible.size).toInt)
            map(mp.r)(mp.c).imObjOpt = Some(Forest())
  
}
