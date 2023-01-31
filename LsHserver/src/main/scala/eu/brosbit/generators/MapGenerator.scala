package eu.brosbit.generators

import eu.brosbit.tiles.*

import scala.util.Random
import scala.util.control.Breaks.{break, breakable}

case class HexPoint(r: Int, c: Int)

class MapGenerator(val sizeXY: Int):

  val worldTiles:Array[Array[Tile]] = Array.ofDim[Tile](sizeXY, sizeXY).map(row => row.map(x => new Plain))
  val allPools = sizeXY*sizeXY
  println("Size of map: " + allPools)
  generateMap()
  
  def getMapStringJson() = "[" + worldTiles
    .map(line => "[" +line.map(tile => "\""+tile.aType.shortName+"\"").mkString(", ") + "]\n")
    .mkString(", ") + "]"

  def getMapStringForJson = worldTiles.map(arr => arr.map( t => t.aType.shortName)) 

  def getMap:Array[Array[Tile]] = worldTiles

  def generateMap(): Unit =
    mainRiver()
    makeChainOfMounts(allPools/20)
    makeRandomMountains(allPools/30)
    var usedHills = addHillsSurroundMounts()
    //    // ///// TODO: surrounding mountains not work!!!
    println("Hills Souranding mountains " + usedHills)
    while usedHills < allPools/4 do
      val position = drawRandomFreePosition(Hill())
      usedHills += 1
      usedHills += setRandomHillsNear(position)

    println("In the end used hills: " + usedHills)
    
    var usedWaters = 0
    usedWaters +=  makeLakes(allPools/20, 3)
    println("Used waters for Lakes: " + usedWaters)
    makeSteppes(allPools/10, 10)
  


  def makeChainOfMounts(mountainsNumb:Int):Unit =
      //sea repair bad
      // for(let i = 0; i < this.sizeXY; i++){
      //   this.worldTiles[i][this.sizeXY -1] = 'w';
      // }
      //water
      //val mountainS = mountainsNumb;
    var mountain = mountainsNumb;
    while mountain > 0  do
      var randChain = Math.floor((Math.random() * 8 + 6))
      if (randChain > mountain) randChain = mountain
        //console.log("to create mountains: " + randChain);
      var point = drawRandomFreePosition(Mountain())
      mountain -= 1
      randChain -= 1
      if (randChain != 0 && mountain != 0) then
        val direction = Math.floor(Math.random() * 6).toInt;
        val newPoint = poolForDir(point.r, direction);
        //console.log("randomPoint: " + point.x + "," + point.y);
        //println("newPoint: " + newPoint.r + "," + newPoint.c);
        if checkGoodPointPos(HexPoint(newPoint.r + point.r, newPoint.c + point.c)) &&
          worldTiles(point.r)(point.c).aType.shortName == Plain.shortName then
          worldTiles(point.r)(point.c) = Mountain();
          mountain -= 1
          randChain -= 1
          var tryNum = 0
          while randChain > 0 && mountain > 0 && tryNum < 1000 do
            tryNum += 1
            val newDir = makeNewDirForLinePools(direction)
            val newPoint = poolForDir(point.r, newDir)
            val extraPoint = HexPoint(newPoint.r + point.r, newPoint.c + point.c)
            //println("newDir: " + newDir + " & newPoint after third and more: " + extraPoint.r + "," + extraPoint.c);
            if checkGoodPointPos(extraPoint) then
              if worldTiles(extraPoint.r)(extraPoint.c).aType.shortName == Plain.shortName then
                worldTiles(extraPoint.r)(extraPoint.c) = Mountain();
                point = extraPoint
                mountain -= 1
                randChain -= 1

          end while
    end while


  private def makeRandomMountains(numb:Int) = for i <- 1 to numb do drawRandomFreePosition(Mountain())

  private def checkGoodPointPos(point: HexPoint) =
    if point.r < 0 || point.r >= sizeXY || point.c < 0 || point.c >= sizeXY then false else true

  private def addHillsSurroundMounts() = {
      var numberOfHills = 0;
      for r <- 0 until sizeXY
          c <- 0 until sizeXY do
       if this.worldTiles(r)(c).aType.shortName == Mountain.shortName then
            numberOfHills += fillWithHillNearMount(r, c)
      //println("Used hills: " + numberOfHills)
      numberOfHills
    }

  private def setRandomHillsNear(pos:HexPoint) =
    var numberOfHills = 0
    for dir <- 0 to 5 do
      val nearDir = poolForDir(pos.r, dir)
      val nearPoint = HexPoint(pos.r + nearDir.r, pos.c + nearDir.c)
      if checkGoodPointPos(nearPoint) && worldTiles(nearPoint.r)(nearPoint.c).aType.shortName == Plain.shortName && Math.random() > 0.5 then
        this.worldTiles(nearPoint.r)(nearPoint.c) = Hill()
        numberOfHills += 1
    numberOfHills

  private def makeLakes(_watersNumber:Int, size:Int) =
    var watersNumber = _watersNumber
    while watersNumber > 0 do
        val dim = Math.ceil(Math.random()*size)
        val source = drawRandomFreePosition(DeepWater())
        watersNumber -= 1;
        watersNumber -= longLakeDraw(source, size)
    _watersNumber - watersNumber

  ///TODO: repair not continues line of water
  def mainRiver(): Unit =
    val random = Random()
    var COL = random.nextInt(sizeXY / 4) + sizeXY / 2
    var ROW = 0
    worldTiles(ROW)(COL) = DeepWater()
    //println("Start main river: " + COL)
    var r = 0
    var ford = 5
    var rFord = 0
    var sideRiverStart: List[HexPoint] = List()
    var longRiverSide = 10
    while ROW < this.sizeXY do
      r = random.nextInt(2)
      if (ROW % 2 == 1) COL -= r
      else COL += r
      if (ford < 6) then
        this.worldTiles(ROW)(COL) = DeepWater()
        ford += 1
        longRiverSide -= 1
        if (longRiverSide <= 0) then
          longRiverSide = 10
          sideRiverStart = HexPoint(ROW, COL) :: sideRiverStart
      else
        rFord = random.nextInt(3)
        //println("rFord " + rFord);
        if (rFord < 1) then
          this.worldTiles(ROW)(COL) = new Ford
          ford = 0
        else
          this.worldTiles(ROW)(COL) = new DeepWater
          longRiverSide -= 1
          ford += 1

      ROW += 1
    //println("Finish main river: " + COL)
    mkSideRivers(sideRiverStart)


  private def mkSideRivers(sideRiverStart: List[HexPoint]): Unit = breakable {
    val random = new Random()
    var weightRandom = 0.5;
    for a <- sideRiverStart do
      //println(s"rivers side start: ' + ${a.r} + ', ' + ${a.c}")
      breakable {
        var COL = a.c
        var ROW = a.r
        var c = 0
        var ford = 4
        var rFord = 0
        var oldROW = 0;
        if (random.nextDouble() > weightRandom) then
          c = 1
          weightRandom += 0.15
        else
          c = -1
          weightRandom -= 0.15
        COL += c
        if COL >= sizeXY then break
        worldTiles(ROW)(COL) = DeepWater()
        COL += c
        if COL >= sizeXY then break
        worldTiles(ROW)(COL) = DeepWater()
        var sideRiverLong = random.nextInt(12) + 8
        var lastUpDownOrLeft = 0
        oldROW = ROW
        var oldUpDownOrLeft = 0
        while sideRiverLong > 0 do
          var upDownOrLeft = random.nextInt(3) - 1
          if Math.random() < 0.9 then ford -= 1
          var waterType = 'w'
          if ford < 0 then
            ford = 4
            waterType = 'f'
          if upDownOrLeft != -oldUpDownOrLeft then ROW += upDownOrLeft
          if upDownOrLeft == 0 || oldROW % 2 != 0 then COL += 1
          if !(COL < 0 || COL >= sizeXY || ROW < 0 || ROW >= sizeXY) then
            worldTiles(ROW)(COL) = if waterType == 'w' then new DeepWater else new Ford
          sideRiverLong -= 1
          oldUpDownOrLeft = upDownOrLeft

          oldROW = ROW
         /*   
          if upDownOrLeft == 0 || lastUpDownOrLeft == -upDownOrLeft then
            //ROW += upDownOrLeft
            COL += r
            if !(COL < 0 || COL >= sizeXY || ROW < 0 || ROW >= sizeXY) then
              worldTiles(ROW)(COL) = if waterType == 'w' then new DeepWater else new Ford
            else if ROW % 2 == 0 then
              ROW += upDownOrLeft
            if r < 0 then COL += r
            if !(COL < 0 || COL >= sizeXY || ROW < 0 || ROW >= sizeXY) then
              worldTiles(ROW)(COL) = if waterType == 'w' then new DeepWater else new Ford
            else if r > 0 then COL += r
            ROW += upDownOrLeft
            if !(COL < 0 || COL >= sizeXY || ROW < 0 || ROW >= sizeXY) then
              worldTiles(ROW)(COL) = if waterType == 'w' then new DeepWater else new Ford

          lastUpDownOrLeft = upDownOrLeft
          */

          
      }
  }

  private def drawRandomFreePosition(tile:Tile) = 
    var posOfWorld = -1
    val pow = sizeXY * sizeXY
    while
      posOfWorld = Math.floor(Math.random()*pow).toInt
      worldTiles(Math.floor(posOfWorld/sizeXY).toInt)(posOfWorld % sizeXY).aType.shortName != Plain.shortName
    do () 
    this.worldTiles(Math.floor(posOfWorld/sizeXY).toInt)(posOfWorld % sizeXY) = tile
    //console.log("posOfWorld: " + posOfWorld);
    HexPoint(posOfWorld % this.sizeXY, Math.floor(posOfWorld/this.sizeXY).toInt)

  private def  poolForDir(row: Int, dir:Int) =
    dir match
      case 0 if row % 2 == 0 =>  HexPoint(-1, -1)
      case 0 if row % 2 == 1 =>  HexPoint(-1, 0)
      case 1 => if row % 2 == 0  then HexPoint(-1, 0) else HexPoint(-1, 1)
      case 2 => HexPoint(0, 1)
      case 3 => if row % 2 == 0 then  HexPoint(1, 0) else HexPoint(1, 1)
      case 4 => if row % 2 == 0 then HexPoint(1, -1) else HexPoint(-1, 1)
      case 5 => HexPoint(0, -1)
      case _ => HexPoint(0,0)
  
  private def makeSteppes(steppeNumber:Int, size:Int) = 
    var source:HexPoint = HexPoint(0,0)
    var steppes = steppeNumber
    while steppes > 4 do
      source = drawRandomFreePosition(Steppe())
      steppes -= 1
      steppes -= steppesGroupDraw(source, size)
  

  private def makeNewDirForLinePools(_dir: Int) =
    ///// TODO: is 50% of forward, change to 80%
    var dir = _dir
    val curve = Math.floor(Math.random()*2).toInt
    if curve == 0 then dir
    else
      var leftOrRight = Math.floor(Math.random()*2).toInt
      if leftOrRight == 0 then leftOrRight = -1
      dir += leftOrRight
      if(dir > 5) dir = 0
      if(dir < 0) dir = 5
      dir

  private def fillWithHillNearMount(r:Int, c:Int) =
    var numbOfHills = 0
    for dir <- 0 to 5 do
      val trans = poolForDir(r, dir);
      val center = HexPoint(r + trans.r, c + trans.c);
      if checkGoodPointPos(center) && worldTiles(center.r)(center.c).aType.shortName == Plain.shortName then
        worldTiles(center.r)(center.c) = Hill();
        numbOfHills += 1;
        ///// TODO: add mayby a second line of hiils
    numbOfHills

  private def longLakeDraw(source:HexPoint, size:Int):Int =
    var watersNumber = 0;
    var nextSource = source
    //println("beging lake source: " + nextSource.x + "," + nextSource.y);
    for i <- 0 until size do
      val arrAddedWater = makeNextLongLakeDrawElement(nextSource)
      watersNumber += arrAddedWater.length;
      //println("for lake chains: " + i + "; " + arrAddedWater.length);
      if(arrAddedWater.length == 0) return watersNumber
      nextSource = arrAddedWater(Math.floor(Math.random()*arrAddedWater.length).toInt)
     //console.log("_longLakeDraw watersNumber: " + watersNumber)
    watersNumber


  private def makeNextLongLakeDrawElement(source: HexPoint) = {
     var arrForLong:List[HexPoint] = Nil
     for(dir <- 0 to 5){
       val nearDir = poolForDir(source.r, dir)
       val nearPoint = HexPoint(source.r+nearDir.r, source.c + nearDir.c)
       if(checkGoodPointPos(nearPoint) && worldTiles(nearPoint.r)(nearPoint.c).aType.shortName == Plain.shortName && Math.random() > 0.3) {
           worldTiles(nearPoint.r)(nearPoint.c) = DeepWater()
           arrForLong = nearPoint::arrForLong
         }
     }
     arrForLong
   }

  private def steppesGroupDraw(source:HexPoint, size:Int) =
    var steppesNumber = 0
    var next = true
    var nextSource = source
    var arrAddedSteppes:List[HexPoint] = Nil
    //println("beging Steppe source: " + nextSource.x + "," + nextSource.y);
    while next && steppesNumber < size do
      arrAddedSteppes = makeNextSteppeDrawElement(nextSource)
      steppesNumber += arrAddedSteppes.length
      //println("for steppe chains: " + i + "; " + arrAddedSteppes.length);
      if arrAddedSteppes.length == 0 then next = false
      else nextSource = arrAddedSteppes(Math.floor(Math.random()*arrAddedSteppes.length).toInt)
    
    //println("Created steppes tiles: " + steppesNumber);
    steppesNumber

  private def makeNextSteppeDrawElement(source:HexPoint):List[HexPoint] =
    var arrForLong:List[HexPoint] = Nil
    for dir <- 0 to 5 do
      val nearDir = poolForDir(source.r, dir)
      val nearPoint = HexPoint(source.r+nearDir.r, source.c + nearDir.c)
      if !(nearPoint.r < 0 || nearPoint.r >= this.sizeXY || nearPoint.c < 0 || nearPoint.c >= this.sizeXY) then
        if worldTiles(nearPoint.r)(nearPoint.c).aType.shortName == Plain.shortName then
          worldTiles(nearPoint.r)(nearPoint.c) = Steppe();
          arrForLong = nearPoint::arrForLong
      
    arrForLong;
   

   //// TODO: Implement!!!
  private def findMountainInCenter() = ' '



