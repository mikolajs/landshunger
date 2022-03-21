class TheWorld {

 constructor(){
   this.loaded = false;
   // this.sizeXY = 100;

   //console.log("size of map " + width + " of " + height);
   this.loadTilesMockup();
   //this.worldTiles[width][height];// form 0,0 as left top point
   //console.log("Size of map: " + this.sizeXY*this.sizeXY);
   //this.test();
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

 loadTilesMockup(){
   let jsonData = [
     ['p', 'p', 'p', 'm', 'm', 'h', 'h', 'p', 'p', 'p'],
     ['p', 'p', 'p', 'm', 'm', 'h', 'h', 'p', 'p', 'p'],
     ['p', 'p', 'p', 'h', 'm', 'h', 'h', 'p', 'p', 'p'],
     ['p', 'p', 'p', 'h', 'h', 'h', 'h', 'p', 'p', 'p'],
     ['p', 'p', 'p', 'p', 'p', 'h', 'h', 'p', 'p', 'p'],
     ['p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'],
     ['p', 'p', 'p', 'p', 'p', 'h', 'p', 'p', 'p', 'p'],
     ['p', 'p', 'p', 'p', 'h', 'p', 'p', 'p', 'p', 'p'],
     ['p', 'p', 'p', 'h', 'p', 'h', 'h', 'p', 'p', 'p'],
     ['p', 'p', 'h', 'm', 'm', 'h', 'h', 'p', 'p', 'p'],
   ];
   this._createWorld(jsonData);

 }

 _createWorld(jsonData){
   //x rows, y cols
   this.X = jsonData.length;
   this.Y = jsonData[0].length;
   this.worldTiles = new Array(this.X);
   for(let i = 0; i < this.X; i++){
     this.worldTiles[i].push(new Array(this.Y));
   }
   console.log("_createWorld X " + this.X + " Y " + this.Y);
   this.loaded = true;
 }

}
