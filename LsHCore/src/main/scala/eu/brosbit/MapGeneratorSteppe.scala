package eu.brosbit

import eu.brosbit.tiles.*
import scala.util.Random
import scala.util.control.NonLocalReturns.*
case class HexPoint(r: Int, c: Int)

class MapGeneratorSteppe(val sizeXY: Int):

  val worldTiles:Array[Array[Tile]] = Array.ofDim[Tile](sizeXY, sizeXY).map(row => row.map(x => new Plain))
  val allPools = sizeXY*sizeXY
  println("Size of map: " + allPools)
  generateMap()

  def getMapJson() = "[" + worldTiles
    .map(line => "[" +line.map(tile => "\""+tile.aType.shortName+"\"").mkString(", ") + "]\n")
    .mkString(", ") + "]"

  def getMap:Array[Array[Tile]] = worldTiles

  def generateMap(): Unit =
    mainRiver()
    
    makeChainOfMounts(allPools/18)
    makeRandomMountains(sizeXY/2)
    var usedHills = addHillsSurroundMounts()
    //    // ///// TODO: surrounding mountains not work!!!
    //while usedHills < allPools/8 do
    //  val position = drawRandomFreePosition(Hill())
    //  usedHills += 1
    //  usedHills += setRandomHillsNear(position)
 
  //    println("In the end used hills: " + usedHills)
  //    var userdWaters = 0
  //    userdWaters += 100
  //    userdWaters +=  makeLakes(200, 4)
  //    println("Used waters for Lakes: " + userdWaters)
  //    makeSteppes(700, 10)



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
    watersNumber

  def mainRiver(): Unit =
    val random = Random()
    var start = sizeXY / 8 - random.nextInt(sizeXY / 4) + sizeXY / 2
    worldTiles(0)(start) = new DeepWater
    println("Start main river: " + start)
    var ROW = 1
    var r = 0
    var ford = 2 + random.nextInt(5)
    var rFord = 0
    var sideRiverStart: List[HexPoint] = List()
    var longRiverSide = 10
    while ROW < this.sizeXY do
      r = random.nextInt(2)
      if (ROW % 2 == 1) start += r
      else start -= r
      if (ford > 0) then
        this.worldTiles(ROW)(start) = new DeepWater
        if (longRiverSide <= 0) then
          longRiverSide = 10
          sideRiverStart = HexPoint(ROW, start) :: sideRiverStart
      else
        longRiverSide -= 1
        this.worldTiles(ROW)(start) = new Ford
        ford = 5 + random.nextInt(5)
      ford -= 1
      ROW += 1
    //console.log("longRiverSide: " + longRiverSide);
    println("Finish main river: " + start)
    mkSideRivers(sideRiverStart)


  //TODO: Implement
  private def mkSideRivers(sideRiverStart: List[HexPoint]): Unit = {
    val random = new Random()
    var weightRandom = 0.5;
    for a <- sideRiverStart do
      println(s"rivers side start: ' + a[0] + ', ' + a[1]");
      var COL = a.c
      var ROW = a.r
      var r = 0
      var ford = 4
      var rFord = 0
      if (random.nextDouble() > weightRandom) then
        r = 1
        weightRandom += 0.15
      else
        r = -1
        weightRandom -= 0.15
      COL += r
      worldTiles(COL)(ROW) = new DeepWater
      COL += r
      worldTiles(COL)(ROW) = new DeepWater
      var sideRiverLong = random.nextInt(12) + 8
      var lastUpDownOrLeft = 0
      while (sideRiverLong > 0) do
        var upDownOrLeft = random.nextInt(3) - 1
        if Math.random() < 0.9 then ford -= 1
        var waterType = 'w'
        if ford < 0 then
          ford = 4
          waterType = 'f'
        if upDownOrLeft == 0 || lastUpDownOrLeft == -upDownOrLeft then
          COL += r
          if !(COL < 0 || COL >= sizeXY || ROW < 0 || ROW >= sizeXY) then
            worldTiles(COL)(ROW) = if waterType == 'w' then new DeepWater else new Ford
          else if ROW % 2 == 0 then
            ROW += upDownOrLeft
          if r < 0 then COL += r
          if !(COL < 0 || COL >= sizeXY || ROW < 0 || ROW >= sizeXY) then
            worldTiles(COL)(ROW) = if waterType == 'w' then new DeepWater else new Ford
          else if r > 0 then COL += r
          ROW += upDownOrLeft
          if !(COL < 0 || COL >= sizeXY || ROW < 0 || ROW >= sizeXY) then
            worldTiles(COL)(ROW) = if waterType == 'w' then new DeepWater else new Ford

        lastUpDownOrLeft = upDownOrLeft
        sideRiverLong -= 1
  }

  def drawRandomFreePosition(tile:Tile) =
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
  /*
    _makeSteppes(steppeNumber, size){
      let source;
      while(steppeNumber > 4) {
        source = this._drawRandomFreePosition('s');
        steppeNumber--;
        steppeNumber -= this._steppesGroupDraw(source, size);
      }
    }

 */

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

  private def longLakeDraw(source:HexPoint, size:Int):Int = returning {
    var watersNumber = 0;
    var nextSource = source
    //println("beging lake source: " + nextSource.x + "," + nextSource.y);
    for i <- 0 until size do
      val arrAddedWater = makeNextLongLakeDrawElement(nextSource)
      watersNumber += arrAddedWater.length;
      //println("for lake chains: " + i + "; " + arrAddedWater.length);
      if (arrAddedWater.length == 0) throwReturn(watersNumber)
      nextSource = arrAddedWater(Math.floor(Math.random() * arrAddedWater.length).toInt)
    //console.log("_longLakeDraw watersNumber: " + watersNumber)
    watersNumber
  }



  private def makeNextLongLakeDrawElement(source: HexPoint) =
    var arrForLong:List[HexPoint] = Nil
    for dir <- 0 to 5 do
      val nearDir = poolForDir(source.r, dir)
      val nearPoint = HexPoint(source.r+nearDir.r, source.c + nearDir.c)
      if(checkGoodPointPos(nearPoint) && worldTiles(nearPoint.r)(nearPoint.c).aType.shortName == Plain.shortName && Math.random() > 0.3) then
        worldTiles(nearPoint.r)(nearPoint.c) = DeepWater()
        arrForLong = nearPoint::arrForLong
    arrForLong


  private def steppesGroupDraw(source: HexPoint, size:Int):Int = returning {
    var steppesNumber = 0
    var nextSource = source
    var arrAddedSteppes:List[HexPoint] = Nil
     // console.log("beging Steppe source: " + nextSource.x + "," + nextSource.y);
    for  i <- 1 to size do
      arrAddedSteppes = makeNextSteppeDrawElement(nextSource)
      steppesNumber += arrAddedSteppes.length
       // println("for steppe chains: " + i + "; " + arrAddedSteppes.length);
       if(arrAddedSteppes.length == 0) throwReturn(steppesNumber)
       nextSource = arrAddedSteppes(math.floor(math.random()*arrAddedSteppes.length).toInt)
     // console.log("_longLakeDraw steppesNumber: " + steppesNumber)
    steppesNumber
  }


  private def makeNextSteppeDrawElement(source: HexPoint) =
    var arrForLong:List[HexPoint] = Nil
    for dir <- 0 to 5 do
      val nearDir = this.poolForDir(source.r, dir)
      val nearPoint = HexPoint(source.r+nearDir.r, source.c + nearDir.c);
      if nearPoint.r >= 0 && nearPoint.r < this.sizeXY && nearPoint.c >= 0 && nearPoint.c < this.sizeXY then
        if this.worldTiles(nearPoint.c)(nearPoint.r).aType.shortName == "p" then
          this.worldTiles(nearPoint.c)(nearPoint.r) = Steppe();
          arrForLong = nearPoint::arrForLong

    arrForLong


   //// TODO: Implement!!!
   //private def findMountainInCenter = ???



