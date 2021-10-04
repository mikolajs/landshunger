
class WorldMap {

 constructor(){
   this.sizeXY = 100;
   this.worldTiles = Array(this.sizeXY).fill("").map(x => Array(this.sizeXY).fill('p'));
   console.log("Size of map: " + this.sizeXY*this.sizeXY);

   this.generateMap();
   this.test();
 }

 generateMap(){
   this._makeMounts(800);
   let usedHills = this._addHillsSourrandMonts();
   let position;
   ///// TODO: souranding mountains not work!!!
   while(usedHills < 2000){
     position = this._drawRandomFreePosition('h');
     usedHills++;
     usedHills += this._setRandomHillsNear(position);
   }
   console.log("In the end used hills: " + usedHills);
   let userdWaters = 0;
   userdWaters += 100;
   userdWaters +=  this._makeLakes(100, 3);
   console.log("Used waters for Lakes: " + userdWaters);
   this._makeRivers(5, 800);

 }

 _makeMounts(mountainsNumb){
   //sea repair bad
   // for(let i = 0; i < this.sizeXY; i++){
   //   this.worldTiles[i][this.sizeXY -1] = 'w';
   // }
   //water
   const mountainS = mountainsNumb;
   let mountain = mountainS;

   while(mountain > 0 ){
     let randChain = Math.floor((Math.random()*25+1));
     if(randChain > mountain) randChain = mountain;
     let point = this._drawRandomFreePosition('m');
     mountain--;
     randChain--;
     if(randChain == 0 || mountain == 0) continue;
     let direction = Math.floor(Math.random()*6);
     let newPoint = this._poolForDir(point.x, direction);
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
       this.worldTiles[point.x][point.y] = 'm';
       mountain--;
       randChain--;
       if(randChain == 0 || mountain == 0) continue;
     }
     while(randChain > 0){
       let newDir = this._makeNewDirForLinePools(direction);
       newPoint = this._poolForDir(point.x, newDir);
       point.add(newPoint);
       //console.log("newDir: " + newDir + " & newPoint after third and more: " + newPoint.x + "," + newPoint.y);
       //console.log("point after third and more: " + point.x + "," + point.y);
       if(point.x < 0 || point.x >= this.sizeXY || point.y < 0 || point.y >= this.sizeXY)
         break;
       else {
         if(this.worldTiles[point.x][point.y] == 'p') {
           this.worldTiles[point.x][point.y] = 'm';
           mountain--;
           randChain--;
           if(randChain == 0 || mountain == 0) break;
         } else break;

       }
     }

   }
   console.log("After add mountains left it: " + mountain);
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
     nearDir = this._poolForDir(pos.x, dir);
     nearPoint = new PlainPoint(pos.x+nearDir.x, pos.y + nearDir.y);
     if(nearPoint.x < 0 || nearPoint.x >= this.sizeXY || nearPoint.y < 0 || nearPoint.y >= nearPoint.sizeXY) continue;
     if(this.worldTiles[nearPoint.x][nearPoint.y] == 'p') {
       if(Math.random() > 0.5) {
         this.worldTiles[nearPoint.x][nearPoint.y] = 'm';
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

 _makeRivers(maxRivers, watersNumber){
   console.log("_makeRivers not Implemented");
 }

 _drawRandomFreePosition(tile){
   let posOfWorld = 0;
   let pow = this.sizeXY*this.sizeXY;
   do {
     posOfWorld = Math.floor(Math.random()*pow);
   } while(this.worldTiles[Math.floor(posOfWorld/100)][posOfWorld % 100] != 'p');
    this.worldTiles[Math.floor(posOfWorld/100)][posOfWorld % 100] = tile;
   //console.log("posOfWorld: " + posOfWorld);
   return new PlainPoint(Math.floor(posOfWorld/this.sizeXY), posOfWorld % this.sizeXY);
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
     center = new PlainPoint(r + trans.x, c + trans.y);
     if(center.x >= this.sizeXY || center.x < 0 || center.y >= this.sizeXY || center.y < 0) continue;
     if(this.worldTiles[center.x][center.y] == 'p'){
       this.worldTiles[center.x][center.y] = 'h';
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
     let nearDir = this._poolForDir(source.x, dir);
     let nearPoint = new PlainPoint(source.x+nearDir.x, source.y + nearDir.y);
     if(nearPoint.x < 0 || nearPoint.x >= this.sizeXY || nearPoint.y < 0 || nearPoint.y >= nearPoint.sizeXY) continue;
     if(this.worldTiles[nearPoint.x][nearPoint.y] == 'p') {
       if(Math.random() > 0.3) {
         this.worldTiles[nearPoint.x][nearPoint.y] = 'w';
         arrForLong.push(nearPoint);
       }
     }
   }
    return arrForLong;
 }



 test(){
   for(let i = 0; i < this.sizeXY; i++){
     console.log(this.worldTiles[i].join(''));
   }

 }

}
