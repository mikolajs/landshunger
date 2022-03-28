package eu.brosbit.hexlib

import eu.brosbit.tiles.*

import scala.util.Random

case class HexPoint(val row: Int, val col: Int)

case class PlainPoint(val x: Int, val y: Int)

class MapGenerator(val sizeXY: Int):
  //this.sizeXY = 100;
  // this.worldTiles[widht][height] form 0,0 as left top point
  val worldTiles:Array[Array[Tile]] = Array.ofDim[Tile](sizeXY, sizeXY).map(row => row.map(x => new Plain))
  println("Size of map: " + this.sizeXY * this.sizeXY);

  generateMap()
  
  def getMapJson() = "[" + worldTiles
    .map(line => "[" +line.map(tile => "\""+tile.shortName+"\"").mkString(", ") + "]\n")
    .mkString(", ") + "]"
  def getMap:Array[Array[Tile]] = worldTiles

  //this.test();
  def generateMap(): Unit =
    mainRiver()
    //    makeChainOfMounts(800)
    //    makeRandomMountains(30)
    //    var usedHills = addHillsSourrandMonts()
    //    var position = 0;
    //    // ///// TODO: souranding mountains not work!!!
    //    while(usedHills < 2000){
    //      position = drawRandomFreePosition('h')
    //      usedHills++
    //      usedHills += setRandomHillsNear(position)
    //    }
    //    println("In the end used hills: " + usedHills)
    //    var userdWaters = 0
    //    userdWaters += 100
    //    userdWaters +=  makeLakes(200, 4)
    //    println("Used waters for Lakes: " + userdWaters)
    //    makeSteppes(700, 10)


  /*
    def makeChainOfMounts(mountainsNumb:Int):Unit = {
      //sea repair bad
      // for(let i = 0; i < this.sizeXY; i++){
      //   this.worldTiles[i][this.sizeXY -1] = 'w';
      // }
      //water
      //val mountainS = mountainsNumb;
      var mountain = mountainsNumb;
  
      while(mountain > 0 ) {
        var randChain = Math.floor((Math.random() * 20 + 8));
        if (randChain > mountain) randChain = mountain;
        //console.log("to create mountains: " + randChain);
        var point = drawRandomFreePosition('m');
        mountain -= 1
        randChain -= 1
        if (randChain != 0 && mountain != 0) {
        val direction = Math.floor(Math.random() * 6);
        val newPoint = this._poolForDir(point.y, direction);
        //console.log("randomPoint: " + point.x + "," + point.y);
        //console.log("newPoint: " + newPoint.x + "," + newPoint.y);
        if (newPoint.x == 0 && newPoint.y == 0) {
          
        } else {
          point.add(newPoint);
          //console.log("Mountains: " + mountain + " begin mountain chain " + randChain + " dir: " + direction);
          //console.log("point after next: " + point.x + "," + point.y);
          if (point.x < 0 || point.x >= this.sizeXY || point.y < 0 || point.y >= this.sizeXY)
            continue;
          else {
            //console.log("set next Mountain");
            if (this.worldTiles[point.y][point.x] == 'p') {
              this.worldTiles[point.y][point.x] = 'm';
              mountain --;
              randChain --;
            } else continue;
            if (randChain == 0 || mountain == 0) continue;
          }
          while (randChain > 0) {
            val newDir = makeNewDirForLinePools(direction);
            newPoint = poolForDir(point.y, newDir);
            point.add(newPoint);
            //console.log("newDir: " + newDir + " & newPoint after third and more: " + newPoint.x + "," + newPoint.y);
            //console.log("point after third and more: " + point.x + "," + point.y);
            if (point.x < 0 || point.x >= this.sizeXY || point.y < 0 || point.y >= this.sizeXY)
              break;
            else {
              if (this.worldTiles[point.y][point.x] == 'p') {
                this.worldTiles[point.y][point.x] = 'm';
                mountain --;
                randChain --;
                if (randChain == 0 || mountain == 0) break;
              } else break;
  
            }
          }
        }
      }
      }
      console.log("After add mountains left it: " + mountain);
    }
  
    _makeRandomMountains(numb){
      for(let i = 0; i < numb; i++){
        this._drawRandomFreePosition('m');
      }
    }
  
    _addHillsSourrandMonts(){
      let numberOfHills = 0;
      for(let r = 0; r < this.sizeXY; r++){
        for(let c = 0; c < this.sizeXY; c++){
          if(this.worldTiles[r][c] == 'm'){
            numberOfHills += this._fillWithHillNearMount(r, c);
          }
        }
      }
      console.log("Used hills: " + numberOfHills);
      return numberOfHills;
    }
  
    _setRandomHillsNear(pos){
      const posConst = pos;
      let numberOfHills = 0;
      let nearDir;
      let nearPoint;
      for(let dir = 0; dir < 6; dir++){
        nearDir = this._poolForDir(pos.y, dir);
        nearPoint = new PlainPoint(pos.x + nearDir.x, pos.y + nearDir.y);
        if(nearPoint.x < 0 || nearPoint.x >= this.sizeXY || nearPoint.y < 0 || nearPoint.y >= this.sizeXY) continue;
        if(this.worldTiles[nearPoint.y][nearPoint.x] == 'p') {
          if(Math.random() > 0.5) {
            this.worldTiles[nearPoint.y][nearPoint.x] = 'h';
            numberOfHills++;
          }
        }
      }
      return numberOfHills;
    }
  
    _makeLakes(watersNumber, size){
      let source;
      let dim;
      let arrForLong = [];
      while (watersNumber > 0) {
        dim = Math.ceil(Math.random()*size);
        source = this._drawRandomFreePosition('w');
        watersNumber--;
        watersNumber -= this._longLakeDraw(source, size);
      }
      return watersNumber;
    }
  */

  private def mainRiver(): Unit =
    val random = Random()
    var start = random.nextInt(sizeXY / 4) + sizeXY / 2
    worldTiles(start)(0) = new DeepWater
    println("Start main river: " + start)
    var ROW = 1
    var r = 0
    var ford = 5
    var rFord = 0
    var sideRiverStart: List[HexPoint] = List()
    var longRiverSide = 10
    while ROW < this.sizeXY do
      r = random.nextInt(2)
      if (ROW % 2 == 1) start -= r
      else start += r
      if (ford < 6) then
        this.worldTiles(start)(ROW) = new DeepWater
        ford += 1
        longRiverSide -= 1
        if (longRiverSide <= 0) then
          longRiverSide = 10
          sideRiverStart = HexPoint(start, ROW) :: sideRiverStart
      else
        rFord = random.nextInt(4)
        //println("rFord " + rFord);
        if (rFord < 1) then
          this.worldTiles(start)(ROW) = new Ford
          ford = 0
        else
          this.worldTiles(start)(ROW) = new DeepWater
          longRiverSide -= 1
          ford += 1

      ROW += 1
      //console.log("longRiverSide: " + longRiverSide);

    println("Finish main river: " + start)
    mkSideRivers(sideRiverStart)


  //TODO: Implement
  private def mkSideRivers(sideRiverStart: List[HexPoint]): Unit =
    //console.log("_mkSideRivers not Implemented");
    val random = new Random()
    var weightRandom = 0.5;
    for (a <- sideRiverStart) do
      println(s"rivers side start: ' + a[0] + ', ' + a[1]");
      var COL = a.col
      var ROW = a.row
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
      this.worldTiles(COL)(ROW) = new DeepWater
      COL += r
      this.worldTiles(COL)(ROW) = new DeepWater
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
          if !(COL < 0 || COL >= this.sizeXY || ROW < 0 || ROW >= this.sizeXY)  then
            this.worldTiles(COL)(ROW) = if waterType == 'w' then new DeepWater else new Ford 
        else if ROW % 2 == 0 then
          ROW += upDownOrLeft
          if r < 0 then COL += r
          if !(COL < 0 || COL >= this.sizeXY || ROW < 0 || ROW >= this.sizeXY) then
            this.worldTiles(COL)(ROW) = if waterType == 'w' then new DeepWater else new Ford
        else
          if r > 0 then COL += r
          ROW += upDownOrLeft
          if !(COL < 0 || COL >= this.sizeXY || ROW < 0 || ROW >= this.sizeXY) then
            this.worldTiles(COL)(ROW) = if waterType == 'w' then new DeepWater else new Ford

        lastUpDownOrLeft = upDownOrLeft
        sideRiverLong -= 1




  /*
    _makeSteppes(steppeNumber, size){
      let source;
      while(steppeNumber > 4) {
        source = this._drawRandomFreePosition('s');
        steppeNumber--;
        steppeNumber -= this._steppesGroupDraw(source, size);
      }
    }
  
    _drawRandomFreePosition(tile){
      let posOfWorld = 0;
      let pow = this.sizeXY*this.sizeXY;
      do {
        posOfWorld = Math.floor(Math.random()*pow);
      } while(this.worldTiles[Math.floor(posOfWorld/100)][posOfWorld % 100] != 'p');
      this.worldTiles[Math.floor(posOfWorld/100)][posOfWorld % 100] = tile;
      //console.log("posOfWorld: " + posOfWorld);
      return new PlainPoint(posOfWorld % this.sizeXY, Math.floor(posOfWorld/this.sizeXY));
    }
  
    _poolForDir(row, dir){
      let p;
      switch (dir) {
        case 0:
        if(row % 2 == 0) p = new PlainPoint(-1, -1);
        else p = new PlainPoint(-1, 0);
        break;
        case 1:
        if(row % 2 == 0) p = new PlainPoint(-1, 0);
        else p = new PlainPoint(-1, 1);
        break;
        case 2:
          p = new PlainPoint(0, 1);
        break;
        case 3:
        if(row % 2 == 0) p = new PlainPoint(1, 0);
        else p = new PlainPoint(1, 1);
        break;
        case 4:
        if(row % 2 == 0) p = new PlainPoint(1, -1);
        else p = new PlainPoint(-1, 1);
        break;
        case 5:
          p = new PlainPoint(0, -1);
        break;
        default:
          p = new PlainPoint(0,0);
        break;
      }
      return p;
    }
  
    _makeNewDirForLinePools(dir){
      ///// TODO: is 50% of forward, change to 80%
      let curve = Math.floor(Math.random()*2);
      if(curve == 0) {
        return dir;
      } else {
        let leftOrRight = Math.floor(Math.random()*2);
        if(leftOrRight == 0) leftOrRight = -1;
        dir += leftOrRight;
        if(dir > 5) dir = 0;
        if(dir < 0) dir = 5;
        return dir;
      }
    }
  
    _fillWithHillNearMount(r, c){
      let numbOfHills = 0;
      let center;
      let trans;
      for(let dir = 0; dir < 6; dir++){
        trans = this._poolForDir(r, dir);
        center = new PlainPoint(c + trans.y, r + trans.x);
        if(center.x >= this.sizeXY || center.x < 0 || center.y >= this.sizeXY || center.y < 0) continue;
        if(this.worldTiles[center.y][center.x] == 'p'){
          this.worldTiles[center.y][center.x] = 'h';
          numbOfHills++;
          ///// TODO: add mayby a second line of hiils
        }
      }
      return numbOfHills;
    }
  
    _longLakeDraw(source, size){
      let watersNumber = 0;
      let nextSource = source;
      let arrAddedWater = [];
      //console.log("beging lake source: " + nextSource.x + "," + nextSource.y);
      for(let i = 0; i < size; i++){
        arrAddedWater = this._makeNextLongLakeDrawElement(nextSource);
        watersNumber += arrAddedWater.length;
        //console.log("for lake chains: " + i + "; " + arrAddedWater.length);
        if(arrAddedWater.length == 0) break;
        nextSource = arrAddedWater[Math.floor(Math.random()*arrAddedWater.length)];
      }
      //console.log("_longLakeDraw watersNumber: " + watersNumber);
      return watersNumber;
    }
  
    _makeNextLongLakeDrawElement(source){
      let arrForLong = [];
      for(let dir = 0; dir < 6; dir++){
        let nearDir = this._poolForDir(source.y, dir);
        let nearPoint = new PlainPoint(source.x+nearDir.x, source.y + nearDir.y);
        if(nearPoint.x < 0 || nearPoint.x >= this.sizeXY || nearPoint.y < 0 || nearPoint.y >= this.sizeXY) continue;
        if(this.worldTiles[nearPoint.y][nearPoint.x] == 'p') {
          if(Math.random() > 0.3) {
            this.worldTiles[nearPoint.y][nearPoint.x] = 'w';
            arrForLong.push(nearPoint);
          }
        }
      }
      return arrForLong;
    }
  
    _steppesGroupDraw(source, size){
      let steppesNumber = 0;
      let nextSource = source;
      let arrAddedSteppes = [];
      // console.log("beging Steppe source: " + nextSource.x + "," + nextSource.y);
      for(let i = 0; i < size; i++){
        arrAddedSteppes = this._makeNextSteppeDrawElement(nextSource);
        steppesNumber += arrAddedSteppes.length;
        // console.log("for steppe chains: " + i + "; " + arrAddedSteppes.length);
        if(arrAddedSteppes.length == 0) break;
        nextSource = arrAddedSteppes[Math.floor(Math.random()*arrAddedSteppes.length)];
      }
      // console.log("_longLakeDraw steppesNumber: " + steppesNumber);
      return steppesNumber;
    }
  
    _makeNextSteppeDrawElement(source){
      let arrForLong = [];
      for(let dir = 0; dir < 6; dir++){
        let nearDir = this._poolForDir(source.y, dir);
        let nearPoint = new PlainPoint(source.x+nearDir.x, source.y + nearDir.y);
        if(nearPoint.x < 0 || nearPoint.x >= this.sizeXY || nearPoint.y < 0 || nearPoint.y >= this.sizeXY) continue;
        if(this.worldTiles[nearPoint.y][nearPoint.x] == 'p') {
          this.worldTiles[nearPoint.y][nearPoint.x] = 's';
          arrForLong.push(nearPoint);
        }
      }
      return arrForLong;
    }
  
    //// TODO: Implement!!!
    _findMountainInCenter(){
  
    }
  
  
    test(){
      for(let i = 0; i < this.sizeXY; i++){
        console.log(this.worldTiles[i].join(''));
      }
  
    }
    
   */

