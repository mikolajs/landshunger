class TheWorld {

 constructor(mapWorld){
   this.loaded = 0;
   this.gameId = '0';
   // this.sizeXY = 100;
  this.host = window.origin + '/json/'
   //console.log("size of map " + width + " of " + height);
   //this.loadTilesMockup2();
   //this.worldTiles[width][height];// form 0,0 as left top point
   //console.log("Size of map: " + this.sizeXY*this.sizeXY);
   //this.test();
   //this.loadObjectsMockup2();
   this.loadTiles();
   this.mapWorld = mapWorld;
 }

 loadTiles(){
  let xhr = new XMLHttpRequest();
  console.log("TheWorld start loadTiles");
  xhr.onreadystatechange = () => {
    console.log("on ready state change " + xhr.status);
    if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
      console.log('LOADED Tiles JSON');
      let jsonData = JSON.parse(xhr.responseText);
      console.log(jsonData);
      this._createTilesMap(jsonData);
      this.loadImmovableObjects();
    }
  }
  xhr.open("GET", this.host + 'tileMap/'+this.gameId, true);
  xhr.send();
 }

 loadImmovableObjects(){
  let xhr = new XMLHttpRequest();
  console.log("TheWorld start loadTiles");
  xhr.onreadystatechange = () => {
    console.log("on ready state change " + xhr.status);
    if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
      console.log('LOADED Tiles JSON');
      let jsonData = JSON.parse(xhr.responseText);
      console.log(jsonData);
      this._createImmovableObjectsMap(jsonData);
    }
  }
  xhr.open("GET", this.host + 'objectMap/' + this.gameId, true);
  xhr.send();
 }

 _createTilesMap(jsonDataFull){
  //x rows, y cols
  this.gameId = jsonDataFull.id;
  let jsonData = jsonDataFull.m;
  this.Y = jsonData.length;
  this.X = jsonData[0].length;
  console.log("TheWorld create Tiles Map size of data: " + this.X + " " + this.Y);
  this.worldTiles = new Array(this.X);
  //console.log(this.worldTiles.length);
  for(let i = 0; i < this.Y; i++){
    this.worldTiles[i] = new Array(this.X);
  }
  for(let i = 0; i < this.Y; i++){
   for(let j = 0; j < this.X; j++) this.worldTiles[i][j] = jsonData[i][j];
 }
  console.log('Loaded objects ' + this.loaded);
 //for(let i in this.worldTiles)
   // for(let j in this.worldTiles[i]) console.log(this.worldTiles[i][j]);
  //console.log("_createWorld X " + this.X + " Y " + this.Y);
}

_createImmovableObjectsMap(jsonDataFull) {
  let gameId = jsonDataFull.id;
  if(gameId != this.gameId) {
    console.log('Error!!! Game id ' + this.gameId + ' not found! Game id found ' + gameId);
    return;
  }
  let jsonData = jsonDataFull.m;
  let Y = jsonData.length;
  let X = jsonData[0].length;
  let obj = {}
  console.log("TheWorld create Immovable Objects Map size of data: " + X + " " + Y);
  this.worldObjects = new Array(X);
  for(let i = 0; i < Y; i++){
     this.worldObjects[i] = new Array(X);
  }
  for(let i = 0; i < Y; i++){
    for(let j = 0; j < X; j++){
      obj = jsonData[i][j];
      if(obj.n == 'f0') {
        if(obj.hp > 800) obj.n = 'f4';
        else if(obj.hp > 600) obj.n = 'f3';
        else if(obj.hp > 400) obj.n = 'f2';
        else if(obj.hp > 200) obj.n = 'f1';
      }
      this.worldObjects[i][j] = obj;
    }
  }
  console.log('Loaded objects ' + this.loaded);
  this.mapWorld.drawMapAsynchronic();
}

 loadTilesMockup2(){
  let jsonString =[["st", "wd", "hi", "mo", "mo", "mo", "hi", "mo", "mo", "mo", "mo", "mo", "hi", "pl", "hi", "hi", "mo", "mo", "mo", "hi", "pl", "wd", "wd", "hi", "mo", "hi", "pl", "pl", "pl", "pl"]
, ["st", "wd", "hi", "hi", "hi", "mo", "mo", "hi", "mo", "hi", "hi", "hi", "pl", "hi", "mo", "mo", "hi", "mo", "hi", "hi", "hi", "wd", "wd", "hi", "hi", "pl", "pl", "pl", "pl", "pl"]
, ["wd", "pl", "wd", "wd", "pl", "pl", "hi", "hi", "pl", "hi", "pl", "pl", "pl", "pl", "pl", "hi", "hi", "pl", "hi", "hi", "mo", "wd", "pl", "pl", "pl", "pl", "pl", "pl", "pl", "pl"]
, ["hi", "wd", "wd", "pl", "pl", "hi", "mo", "hi", "pl", "pl", "pl", "pl", "wd", "pl", "pl", "st", "st", "pl", "hi", "mo", "hi", "wd", "pl", "wd", "pl", "st", "pl", "pl", "pl", "pl"]
, ["mo", "hi", "pl", "pl", "pl", "pl", "pl", "hi", "st", "pl", "pl", "st", "pl", "pl", "pl", "st", "st", "st", "pl", "pl", "hi", "wd", "pl", "pl", "pl", "pl", "wd", "pl", "pl", "pl"]
, ["hi", "pl", "pl", "pl", "hi", "hi", "st", "st", "st", "pl", "pl", "pl", "pl", "pl", "st", "st", "st", "pl", "pl", "pl", "st", "wd", "pl", "pl", "pl", "pl", "pl", "pl", "pl", "pl"]
, ["pl", "st", "pl", "wd", "hi", "mo", "hi", "st", "st", "st", "hi", "hi", "hi", "hi", "pl", "st", "st", "st", "pl", "pl", "pl", "fd", "hi", "hi", "pl", "pl", "pl", "hi", "hi", "hi"]
, ["hi", "hi", "hi", "hi", "hi", "hi", "st", "st", "st", "hi", "mo", "hi", "mo", "hi", "st", "st", "hi", "hi", "hi", "hi", "hi", "fd", "mo", "hi", "pl", "pl", "hi", "mo", "mo", "hi"]
, ["hi", "mo", "hi", "mo", "hi", "hi", "pl", "st", "st", "pl", "pl", "hi", "mo", "hi", "st", "st", "hi", "mo", "mo", "mo", "mo", "wd", "hi", "hi", "pl", "pl", "pl", "pl", "hi", "hi"]
, ["hi", "hi", "hi", "hi", "mo", "hi", "pl", "pl", "pl", "pl", "hi", "mo", "hi", "st", "st", "st", "hi", "mo", "mo", "hi", "hi", "wd", "mo", "hi", "pl", "wd", "pl", "pl", "pl", "pl"]
, ["pl", "pl", "wd", "wd", "wd", "hi", "st", "st", "st", "pl", "hi", "mo", "mo", "hi", "st", "st", "st", "pl", "hi", "hi", "wd", "wd", "st", "hi", "st", "wd", "wd", "wd", "pl", "hi"]
, ["pl", "wd", "wd", "wd", "st", "st", "st", "st", "st", "pl", "hi", "mo", "hi", "st", "hi", "hi", "hi", "hi", "hi", "pl", "wd", "st", "st", "st", "st", "wd", "wd", "pl", "hi", "mo"]
, ["pl", "pl", "wd", "wd", "wd", "hi", "hi", "st", "st", "pl", "hi", "mo", "hi", "hi", "hi", "mo", "mo", "mo", "mo", "hi", "wd", "pl", "st", "st", "st", "st", "wd", "wd", "wd", "pl"]
, ["pl", "pl", "wd", "wd", "hi", "mo", "hi", "pl", "pl", "st", "hi", "mo", "hi", "mo", "hi", "mo", "hi", "hi", "hi", "pl", "wd", "hi", "hi", "hi", "hi", "wd", "wd", "wd", "pl", "pl"]
, ["pl", "pl", "pl", "pl", "pl", "pl", "hi", "pl", "hi", "hi", "hi", "mo", "hi", "pl", "hi", "mo", "mo", "hi", "pl", "pl", "wd", "mo", "mo", "mo", "mo", "hi", "wd", "wd", "pl", "pl"]
, ["pl", "pl", "pl", "pl", "pl", "pl", "pl", "hi", "mo", "hi", "mo", "hi", "pl", "pl", "hi", "hi", "mo", "hi", "pl", "pl", "wd", "mo", "hi", "mo", "hi", "hi", "hi", "hi", "pl", "pl"]
, ["pl", "pl", "pl", "wd", "pl", "pl", "st", "pl", "pl", "hi", "mo", "hi", "pl", "pl", "pl", "pl", "pl", "hi", "pl", "pl", "hi", "hi", "hi", "hi", "mo", "hi", "mo", "mo", "hi", "pl"]
, ["wd", "wd", "wd", "pl", "fd", "wd", "pl", "pl", "hi", "mo", "hi", "hi", "hi", "pl", "pl", "pl", "pl", "pl", "pl", "hi", "mo", "hi", "pl", "hi", "mo", "mo", "mo", "mo", "hi", "pl"]
, ["mo", "hi", "pl", "hi", "hi", "pl", "wd", "wd", "pl", "pl", "hi", "hi", "mo", "hi", "pl", "pl", "pl", "pl", "pl", "pl", "pl", "hi", "pl", "pl", "pl", "wd", "wd", "wd", "hi", "pl"]
, ["hi", "pl", "hi", "mo", "hi", "st", "hi", "hi", "wd", "wd", "pl", "hi", "hi", "wd", "pl", "fd", "pl", "pl", "pl", "pl", "pl", "wd", "pl", "wd", "wd", "pl", "hi", "mo", "wd", "fd"]
, ["pl", "pl", "pl", "pl", "hi", "st", "hi", "mo", "mo", "hi", "wd", "wd", "wd", "hi", "wd", "hi", "wd", "wd", "wd", "wd", "wd", "st", "wd", "hi", "hi", "hi", "pl", "hi", "hi", "pl"]
, ["pl", "pl", "pl", "pl", "st", "st", "hi", "hi", "hi", "mo", "mo", "hi", "mo", "mo", "mo", "mo", "mo", "mo", "hi", "st", "st", "st", "hi", "mo", "mo", "hi", "hi", "mo", "hi", "pl"]
, ["pl", "pl", "pl", "st", "st", "st", "hi", "mo", "mo", "mo", "hi", "mo", "mo", "hi", "hi", "mo", "hi", "mo", "mo", "hi", "hi", "hi", "pl", "pl", "hi", "hi", "pl", "pl", "hi", "pl"]
, ["pl", "pl", "wd", "st", "st", "st", "hi", "mo", "hi", "hi", "hi", "hi", "hi", "pl", "hi", "hi", "hi", "hi", "mo", "hi", "mo", "hi", "pl", "st", "st", "pl", "pl", "pl", "pl", "pl"]
, ["pl", "pl", "st", "st", "st", "st", "pl", "pl", "hi", "pl", "hi", "mo", "hi", "pl", "pl", "pl", "pl", "hi", "mo", "hi", "hi", "mo", "hi", "st", "st", "st", "st", "pl", "pl", "pl"]
, ["pl", "hi", "hi", "wd", "wd", "pl", "pl", "pl", "pl", "pl", "hi", "hi", "pl", "st", "wd", "pl", "hi", "mo", "mo", "hi", "mo", "mo", "hi", "st", "st", "st", "st", "pl", "pl", "pl"]
, ["pl", "hi", "mo", "hi", "wd", "pl", "pl", "pl", "pl", "pl", "wd", "pl", "pl", "pl", "pl", "pl", "pl", "pl", "hi", "mo", "mo", "mo", "hi", "pl", "pl", "st", "st", "pl", "pl", "pl"]
, ["pl", "hi", "hi", "wd", "wd", "hi", "hi", "wd", "pl", "pl", "pl", "pl", "wd", "pl", "pl", "pl", "pl", "pl", "hi", "mo", "mo", "mo", "hi", "pl", "hi", "hi", "pl", "pl", "pl", "pl"]
, ["pl", "pl", "pl", "pl", "pl", "hi", "mo", "hi", "pl", "pl", "pl", "hi", "hi", "pl", "pl", "pl", "pl", "pl", "pl", "pl", "hi", "hi", "hi", "pl", "hi", "mo", "hi", "pl", "pl", "pl"]
, ["pl", "pl", "pl", "pl", "pl", "hi", "hi", "pl", "pl", "pl", "hi", "mo", "hi", "pl", "pl", "pl", "pl", "pl", "pl", "pl", "pl", "pl", "pl", "pl", "hi", "hi", "pl", "pl", "pl", "pl"]];
  this._createWorld(jsonString);
 }
 loadObjectsMockup2(){
  let jsonString = 
  [['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']
, ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']];
   this._createObjects(jsonString);
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
     ['pl', 'pl', 'pl', 'mo', 'mo', 'hi', 'hi', 'pl', 'pl', 'pl'],
     ['pl', 'pl', 'pl', 'mo', 'mo', 'hi', 'hi', 'wo', 'wo', 'pl'],
     ['st', 'pl', 'pl', 'hi', 'mo', 'hi', 'hi', 'pl', 'fd', 'pl'],
     ['st', 'st', 'pl', 'hi', 'hi', 'hi', 'hi', 'pl', 'pl', 'pl'],
     ['pl', 'st', 'pl', 'pl', 'pl', 'hi', 'hi', 'pl', 'pl', 'pl'],
     ['pl', 'pl', 'pl', 'pl', 'pl', 'pl', 'pl', 'hu', 'hu', 'pl'],
     ['pl', 'pl', 'pl', 'pl', 'pl', 'hi', 'hu', 'hu', 'pl', 'pl'],
     ['pl', 'pl', 'pl', 'pl', 'hi', 'pl', 'pl', 'pl', 'pl', 'pl'],
     ['pl', 'pl', 'pl', 'hi', 'pl', 'hi', 'hi', 'pl', 'pl', 'pl'],
     ['pl', 'pl', 'hi', 'mo', 'mo', 'hi', 'hi', 'pl', 'pl', 'pl'],
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



}
