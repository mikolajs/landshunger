class TheWorld {

 constructor(){
   this.loaded = false;
   // this.sizeXY = 100;

   //console.log("size of map " + width + " of " + height);
   this.loadTilesMockup();
   //this.worldTiles[width][height];// form 0,0 as left top point
   //console.log("Size of map: " + this.sizeXY*this.sizeXY);
   //this.test();
   this.loadObjectsMockup();
    this.loaded = true;
 }

 loadTiles(){
   let xhr = new XMLHttpRequest();
   xhr.onreadystatechange = () => {
    if(this.readyState == 4 && this.status == 200){
      let jsonData = JSON.parse(xhr.responseText);
      this._createWorld(jsonData);
      this.loaded = true;
    }
   }
   xhr.open("GET", 'http://localhost:3030', true);
   xhr.send();
 }

 loadObjects(){


 }

 loadObjectsMockup(){
   let jsonData = [
     ['bs', 'bm', 'bl', '', '', '', '', '', '', ''],
     ['ds', 'dm', 'dl', 'og', '', '', 'ow', '', '', ''],
     ['oe', 'os', '', '', '', '', 'ow', '', '', ''],
      ['os', 'ol', 'om', '', 'us', 'us', '', '', '', ''],
      ['om', 'ol', 'ol', '', '', '', '', 'oc', 'oc', 'oc'],
      ['om', 'ol', 'os', '', 'us', 'ua', 'uc', 'oc', 'oc', ''],
      ['', 'om', 'om', '', '', 'ua', 'uc', 'oc', 'ov', ''],
      ['', 'os', 'oe', '', '', '', '', 'oc', 'oc', ''],
      ['ss', 'sm', 'sl', '', '', '', '', '', 'uc', ''],
      ['', '', '', '', '', '', '', '', '', '']
   ];
   this._createObjects(jsonData);
 }

 loadTilesMockup(){
   let jsonData = [
     ['p', 'p', 'p', 'm', 'm', 'h', 'h', 'p', 'p', 'p'],
     ['p', 'p', 'p', 'm', 'm', 'h', 'h', 'w', 'w', 'p'],
     ['s', 'p', 'p', 'h', 'm', 'h', 'h', 'p', 'f', 'p'],
     ['s', 's', 'p', 'h', 'h', 'h', 'h', 'p', 'p', 'p'],
     ['p', 's', 'p', 'p', 'p', 'h', 'h', 'p', 'p', 'p'],
     ['p', 'p', 'p', 'p', 'p', 'p', 'p', 'u', 'u', 'p'],
     ['p', 'p', 'p', 'p', 'p', 'h', 'u', 'u', 'p', 'p'],
     ['p', 'p', 'p', 'p', 'h', 'p', 'p', 'p', 'p', 'p'],
     ['p', 'p', 'p', 'h', 'p', 'h', 'h', 'p', 'p', 'p'],
     ['p', 'p', 'h', 'm', 'm', 'h', 'h', 'p', 'p', 'p'],
   ];
   this._createWorld(jsonData);

 }

 _createObjects(jsonData){
   this.worldObjects = new Array(this.X);
   for(let i = 0; i < this.X; i++){
     this.worldObjects[i] = new Array(this.Y);
   }
   for(let i = 0; i < this.X; i++){
    for(let j = 0; j < this.Y; j++) this.worldObjects[i][j] = jsonData[i][j];
  }

 }

 _createWorld(jsonData){
   //x rows, y cols
   this.Y = jsonData.length;
   this.X = jsonData[0].length;
   //console.log("size of data: " + this.X + " " + this.Y);
   this.worldTiles = new Array(this.X);
   //console.log(this.worldTiles.length);
   for(let i = 0; i < this.X; i++){
     this.worldTiles[i] = new Array(this.Y);
   }
   for(let i = 0; i < this.X; i++){
    for(let j = 0; j < this.Y; j++) this.worldTiles[i][j] = jsonData[i][j];
  }
  //for(let i in this.worldTiles)
    // for(let j in this.worldTiles[i]) console.log(this.worldTiles[i][j]);
   //console.log("_createWorld X " + this.X + " Y " + this.Y);

 }

}
