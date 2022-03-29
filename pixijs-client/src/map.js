class MapWorld {
  constructor(grid, X, Y, container, resources){
    this.grid = grid;
    this.container = container;
    this.resources = resources;
    //let worldsMap = new WorldMap();
    //this.drawMap(worldsMap.worldTiles);
    this.worldData = new TheWorld();
    while(!this.worldData.loaded){
      console.log("waiting");
    }
    //X columns Y rows
    this.X = this.worldData.X;
    this.Y = this.worldData.Y;
    //console.log(this.X + " and " + this.Y);
    this.startX = 0;
    this.startY = 0;
    this.drawMap();//chaange to drawMapTimeout
  }

  drawMapTimeout(){
    if(!this.worldData.loaded) {
      window.setTimeout(() => {this.drawMapTimeout();}, 2000);
      console.log("waiting next 2 seconds");
      return;
    }
    this.drawMap();
  }

  //// TODO: Implement
  drawMap(){
    //// TODO: Change direction x and y (must be the same as in worldmap test())
    // console.log(resources);
    this.container.removeChildren(0,this.container.children.length);
    let startX = this.startX;
    let startY = this.startY;
    /// TODO: must add delete sprite from containder before add new sprites

    let tile = "";
    console.log(this.worldData.worldTiles.length);
    for(let i = 0; i < this.Y; i++){
      for(let j = 0; j < this.X; j++){
        tile = this.worldData.worldTiles[startY+i][startX+j];
        //console.log(tile);
        const sprite = new PIXI.Sprite(this._getTileTexture(tile));
        sprite.anchor.set(0.5);
        const p = this.grid.getCenterOfPoolInPixels(j, i);
        sprite.x = p.x;
        sprite.y = p.y;
        this.container.addChild(sprite);
        //console.log("Title added at " + i + "," + j);
        //console.log(p);
        tile = this.worldData.worldObjects[startY+i][startX+j];
        if(tile == '') continue;
        console.log(tile + " w maps");
        const objSprite = new PIXI.Sprite(this._getTileTexture(tile));
        objSprite.anchor.set(0.5);
        objSprite.x = p.x;
        objSprite.y = p.y;
        this.container.addChild(objSprite);
      }
    }
  }

  scrollUp(){
    if(this.startY >= 2) this.startY -= 2;
    this.drawMap();
  }

  scrollDown(){
    if(this.startY <= this.Y-12) this.startY += 2;
    this.drawMap();
  }
  scrollLeft(){
    if(this.startX >= 2) this.startX -= 2;
    this.drawMap();
  }
  scrollRight(){
    if(this.startX <= this.X-12) this.startX += 2;
    this.drawMap();
  }

  _getTileTexture(tile){
    if(tile == 'p') return this.resources.p.texture;
    else if(tile == 'w') return this.resources.w.texture;
    else if(tile == 'h') return this.resources.h.texture;
    else if(tile == 'm') return this.resources.m.texture;
    else if(tile == 's') return this.resources.s.texture;
    else if(tile == 'f') return this.resources.f.texture;
    else if(tile == 'u') return this.resources.u.texture;
    else if(tile == 'ol') return this.resources.ol.texture;
    else if(tile == 'om') return this.resources.om.texture;
    else if(tile == 'os') return this.resources.os.texture;
    else if(tile == 'oc') return this.resources.oc.texture;
    else if(tile == 'ov') return this.resources.ov.texture;
    else if(tile == 'og') return this.resources.og.texture;
    else if(tile == 'ua') return this.resources.ua.texture;
    else if(tile == 'us') return this.resources.us.texture;
    else if(tile == 'uc') return this.resources.uc.texture;
    else if(tile == 'ow') return this.resources.ow.texture;
    else return this.resources.p.texture;

  }

}
