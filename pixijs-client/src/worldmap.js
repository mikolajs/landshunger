
class WorldMap {

 constructor(width, height){
   this.loaded = false;
   // this.sizeXY = 100;
   this.worldTiles = Array(width)
   this.worldTiles[width][height];// form 0,0 as left top point
   //console.log("Size of map: " + this.sizeXY*this.sizeXY);
   this.readMap();
   this.generateMap();
   //this.test();
 }

 readMap(){
   this.xHttp = new XMLHttpRequest();
   let url = "http://localhost:8008/";
   var self = this;
   this.xHttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        //console.log("get response: " + url);
        //console.log(this.responseText);
        self._readMap(this.responseText);
      }
    }
    this.xHttp.open("GET", url, true);
    this.xHttp.send();
 }

 _readMap(json){
   this.loaded = true;
   let worldMap = JSON.parse(json);
   console.log(json);
   this.sizeXY = worldMap.length;
   this.worldTiles = Array(this.sizeXY).fill("").map(x => Array(this.sizeXY).fill('.'));
   for(let i = 0; i < this.sizeXY; i++){
     for(let j = 0; j < this.sizeXY; j++){
       this.worldTiles[i][j] == worldMap[i][j];
     }
   }


 }

 generateMap(){
   this._mainRiver();
   this._makeChainOfMounts(800);
   this._makeRandomMountains(30);
   let usedHills = this._addHillsSourrandMonts();
   let position;
   // ///// TODO: souranding mountains not work!!!
    while(usedHills < 2000){
     position = this._drawRandomFreePosition('h');
     usedHills++;
     usedHills += this._setRandomHillsNear(position);
   }
    console.log("In the end used hills: " + usedHills);
    let userdWaters = 0;
   userdWaters += 100;
   userdWaters +=  this._makeLakes(200, 4);
   console.log("Used waters for Lakes: " + userdWaters);

   this._makeSteppes(700, 10);

 }

 _makeChainOfMounts(mountainsNumb){
   //sea repair bad
   // for(let i = 0; i < this.sizeXY; i++){
   //   this.worldTiles[i][this.sizeXY -1] = 'w';
   // }
   //water
   const mountainS = mountainsNumb;
   let mountain = mountainS;

   while(mountain > 0 ){
     let randChain = Math.floor((Math.random()*20+8));
     if(randChain > mountain) randChain = mountain;
     //console.log("to create mountains: " + randChain);
     let point = this._drawRandomFreePosition('m');
     mountain--;
     randChain--;
     if(randChain == 0 || mountain == 0) continue;
     let direction = Math.floor(Math.random()*6);
     let newPoint = this._poolForDir(point.y, direction);
     //console.log("randomPoint: " + point.x + "," + point.y);
     //console.log("newPoint: " + newPoint.x + "," + newPoint.y);
     if(newPoint.x == 0 && newPoint.y == 0) continue;
     point.add(newPoint);
     //console.log("Mountains: " + mountain + " begin mountain chain " + randChain + " dir: " + direction);
     //console.log("point after next: " + point.x + "," + point.y);
     if(point.x < 0 || point.x >= this.sizeXY || point.y < 0 || point.y >= this.sizeXY)
       continue;
     else {
       //console.log("set next Mountain");
       if(this.worldTiles[point.y][point.x] == 'p') {
         this.worldTiles[point.y][point.x] = 'm';
         mountain--;
         randChain--;
       } else continue;
       if(randChain == 0 || mountain == 0) continue;
     }
     while(randChain > 0){
       let newDir = this._makeNewDirForLinePools(direction);
       newPoint = this._poolForDir(point.y, newDir);
       point.add(newPoint);
       //console.log("newDir: " + newDir + " & newPoint after third and more: " + newPoint.x + "," + newPoint.y);
       //console.log("point after third and more: " + point.x + "," + point.y);
       if(point.x < 0 || point.x >= this.sizeXY || point.y < 0 || point.y >= this.sizeXY)
         break;
       else {
         if(this.worldTiles[point.y][point.x] == 'p') {
           this.worldTiles[point.y][point.x] = 'm';
           mountain--;
           randChain--;
           if(randChain == 0 || mountain == 0) break;
         } else break;

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

 _mainRiver(){
   let start = Math.floor(Math.random()*this.sizeXY/4) + this.sizeXY/2;
   this.worldTiles[start][0] = 'w';
   console.log("Start main river: " + start);
   let ROW = 1;
   let r;
   let ford = 5;
   let rFord;
   let sideRiverStart = [];
   let longRiverSide = 10;
   while(ROW < this.sizeXY){
     r = Math.floor(Math.random()*2);
     if(ROW % 2 == 1) start -= r;
     else start += r;
     if(ford <  6) {
       this.worldTiles[start][ROW] = 'w';
       ford++;
       longRiverSide--;
       if(longRiverSide <= 0) {
         longRiverSide = 10;
         sideRiverStart.push([start, ROW]);
       }
     } else {
       rFord = Math.floor(Math.random()*4);
       //console.log('rFord ' + rFord);
       if(rFord < 1) {
         this.worldTiles[start][ROW] = 'f';
         ford = 0;
       } else {
         this.worldTiles[start][ROW] = 'w';
         longRiverSide--;
         ford++;
       }
     }
     ROW++;
     //console.log("longRiverSide: " + longRiverSide);
   }
   console.log("Finish main river: " + start);
   this._mkSideRivers(sideRiverStart);
 }

//TODO: Implement
 _mkSideRivers(sideRiverStart){
   //console.log("_mkSideRivers not Implemented");
   let weightRandom = 0.5;
   for(let a of sideRiverStart){
     console.log('rivers side start: ' + a[0] + ', ' + a[1]);

     let COL = a[0];
     let ROW = a[1];
     let r;
     let ford = 4;
     let rFord;

    if(Math.random() > weightRandom) {
      r = 1;
      weightRandom += 0.15;
    } else {
      r = -1;
      weightRandom -= 0.15;
    }
    COL += r;
    this.worldTiles[COL][ROW] = 'w';
    COL += r;
    this.worldTiles[COL][ROW] = 'w';
    let sideRiverLong = Math.floor(Math.random()*12)+8;
    let lastUpDownOrLeft = 0;
    while(sideRiverLong > 0){
      let upDownOrLeft = Math.floor(Math.random()*3)-1;
      if(Math.random() < 0.9) ford--;
      let waterType = 'w'
      if(ford < 0) {
        ford = 4;
        waterType = 'f';
      }
      if(upDownOrLeft == 0 || lastUpDownOrLeft == -upDownOrLeft) {
        COL += r;
        if(COL < 0 || COL >= this.sizeXY || ROW < 0 || ROW >= this.sizeXY) break;
        this.worldTiles[COL][ROW] = waterType;
      } else if(ROW % 2 == 0){
          ROW += upDownOrLeft;
          if(r < 0) COL += r;
        if(COL < 0 || COL >= this.sizeXY || ROW < 0 || ROW >= this.sizeXY) break;
          this.worldTiles[COL][ROW] = waterType;
      } else {
          if(r > 0) COL += r;
          ROW += upDownOrLeft;
        if(COL < 0 || COL >= this.sizeXY || ROW < 0 || ROW >= this.sizeXY) break;
          this.worldTiles[COL][ROW] = waterType;
      }
      lastUpDownOrLeft = upDownOrLeft;
      sideRiverLong--;
    }
    /* while(ROW < this.sizeXY){
      r = Math.floor(Math.random()*2);
      if(ROW % 2 == 1) start -= r;
      else start += r;
      if(ford <  6) {
        this.worldTiles[start][ROW] = 'w';
        ford++;
        longRiverSide--;
        if(longRiverSide == 0) {
          longRiverSide = 15;
          sideRiversCount++;
          sideRiverStart.push([start, ROW]);
        }
      } else {
        rFord = Math.floor(Math.random()*4);
        //console.log('rFord ' + rFord);
        if(rFord < 1) {
          this.worldTiles[start][ROW] = 'f';
          ford = 0;
        } else {
          this.worldTiles[start][ROW] = 'w';
          longRiverSide--;
          ford++;
          }
        }
        ROW++;
      } */
    }
 }

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

}
