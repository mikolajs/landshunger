package eu.brosbit.generators
import  eu.brosbit.tiles.*
import java.util.Random
import eu.brosbit.immovable.Forest
import eu.brosbit.immovable.ImmovableObject
import eu.brosbit.immovable.Grass
import eu.brosbit.hexlib.*

class MapObjectStarterGenerator(map:Array[Array[Tile]]):
  val ROWS = map.size
  val COLS = map(0).size
  val maxSize = ROWS*COLS
  val hex = Hex(ROWS.toShort, COLS.toShort)
  val rand = Random()
  println("Create MapObjectStarterGenerator " + maxSize)
  generate()

  def generate():Unit = 
    randomWood(COLS*ROWS/4)
    allFreeWithGrass()

  def getString =
    map.map(line => 
      line.map(elem => 
        val objStr = if elem.imObjOpt.isEmpty then "\"\""
          else 
            val o = elem.imObjOpt.get
            if o.build then "\"\""
            else o.plant.obj.shortName
        objStr
      ).mkString(",")
    ).mkString("\n") 


  private def randomWood(woodsNumber:Int):Unit = 
    //println("start create Forest")
    val allowed = List(Plain.shortName, Hill.shortName, Humus.shortName)
    var createdWoods = 0
    while createdWoods < woodsNumber do
      val h = randomHexPoint(allowed)
      val forest = Forest()
      forest.setBio((rand.nextDouble()*Forest.maxBio).toShort)
      forest.setHP((rand.nextDouble()*Forest.maxHP).toShort)
      val imm = ImmovableObject(forest)
      map(h.r)(h.c).imObjOpt = Some(imm) 
      createdWoods += 1
      // objectMap(h.r)(h.c) = forest
    //println(s"Created $createdWoods woods")
  
  private def randomHexPoint(allowed:List[String]):HexPoint = 
    var notFound = true
    var hexPoint = HexPoint(0, 0)
    while notFound do
      hexPoint = HexPoint(rand.nextInt(ROWS), rand.nextInt(COLS))
      if allowed.contains(map(hexPoint.r)(hexPoint.c).aType.shortName) then
        notFound = false
    hexPoint
      
  private def allFreeWithGrass() =
    //println("start add Grass")
    val allowed = List(Plain.shortName, Hill.shortName, Humus.shortName, Steppe.shortName)
    for r <- 0 until ROWS do
      for c <- 0 until COLS do
        val tile = map(r)(c).aType.shortName
        if allowed.contains(tile) && map(r)(c).imObjOpt.isEmpty then
          val grass = Grass()
          grass.setBio((rand.nextDouble()*Grass.maxBio).toShort)
          val imm = ImmovableObject(grass)
          map(r)(c).imObjOpt = Some(imm)
