class MapWorld {
  constructor(grid, X, Y, container, resources){
    this.grid = grid;
    this.container = container;
    this.resources = resources;
    //let worldsMap = new WorldMap();
    //this.drawMap(worldsMap.worldTiles);
    this.worldData = new TheWorld(this);
    console.log("start MapWorld");
    this.showX = 10; //size of showed tiles 
    this.showY = 10; //size of showed tiles
    //console.log(this.X + " and " + this.Y);
    this.startX = 0;
    this.startY = 0;
    
  }

  drawMapAsynchronic(){
    console.log('Start mapAsynchonic');
    this.X = this.worldData.X;
    this.Y = this.worldData.Y;
    this.refreshMap();
  }

  refreshMap(){
    this.grid.clearGrid();
    this.grid.drawHexGrid(this.startY);
    this.grid.drawPointIndexes(this.startX, this.startY);
    
    this.drawTilesMap();
    this.drawObjectMap();
  }


  //// TODO: Implement
  drawTilesMap(){
    //// TODO: Change direction x and y (must be the same as in worldmap test())
    // console.log(resources);
    this.container.removeChildren(0,this.container.children.length);
    /// TODO: must add delete sprite from containder before add new sprites?? działa ↑
    let startX = this.startX;
    let startY = this.startY;

    let tile = "";
    console.log(this.worldData.worldTiles.length);
    for(let i = 0; i < this.showY; i++){
      for(let j = 0; j < this.showX; j++){
        tile = this.worldData.worldTiles[startY+i][startX+j];
        //console.log(tile);
        const sprite = new PIXI.Sprite(this._getTileTexture(tile));
        sprite.anchor.set(0.5);
        const p = this.grid.getCenterOfPoolInPixels(j, i, startY);
        sprite.x = p.x; //col
        sprite.y = p.y; //row
        this.container.addChild(sprite);
       
      }
    }
  }

  drawObjectMap(){
    let startX = this.startX;
    let startY = this.startY;

    let obj = {};
    let n = '';
    console.log(this.worldData.worldTiles.length);
    for(let i = 0; i < this.showY; i++){
      for(let j = 0; j < this.showX; j++){
        obj = this.worldData.worldTiles[startY+i][startX+j];
        //console.log(obj);
        const p = this.grid.getCenterOfPoolInPixels(j, i, startY);
        n = this.worldData.worldObjects[startY+i][startX+j].n;
        if(n == '' || n == 'gr' || n == 'pk') continue;
        //console.log(n + " w maps");
        const objSprite = new PIXI.Sprite(this._getTileTexture(n));
        objSprite.anchor.set(0.5);
        objSprite.x = p.x;
        objSprite.y = p.y;
        this.container.addChild(objSprite);
      }
    }

  }

  scrollUp(step){
    if(this.startY >= step) this.startY -= step;
    else this.startY = 0;
    this.refreshMap();
  }

  scrollDown(step){
    if(this.startY <= this.Y-this.showY-step) this.startY += step;
    else this.startY = this.Y - this.showY;
    this.refreshMap();
  }
  scrollLeft(step){
    if(this.startX >= step) this.startX -= step;
    else this.startX = 0;
    this.refreshMap();
  }
  scrollRight(step){
    if(this.startX <= this.X-this.showX-step) this.startX += step;
    else this.startX = this.X - this.showX;
    this.refreshMap();
  }

  _getTileTexture(tile){
    if(tile == 'pl') return this.resources.p.texture;
    else if(tile == 'wd') return this.resources.w.texture;
    else if(tile == 'hi') return this.resources.h.texture;
    else if(tile == 'mo') return this.resources.m.texture;
    else if(tile == 'st') return this.resources.s.texture;
    else if(tile == 'fd') return this.resources.f.texture;
    else if(tile == 'hu') return this.resources.u.texture;
    else if(tile == 'sw') return this.resources.a.texture;
    else if(tile == 'f4') return this.resources.f4.texture;
    else if(tile == 'f3') return this.resources.f3.texture;
    else if(tile == 'f2') return this.resources.f2.texture;
    else if(tile == 'f1') return this.resources.f1.texture;
    else if(tile == 'f0') return this.resources.f0.texture;
    else if(tile == 'c0') return this.resources.c0.texture;
    else if(tile == 'ov') return this.resources.ov.texture;
    else if(tile == 'og') return this.resources.og.texture;
    else if(tile == 'ua') return this.resources.ua.texture;
    else if(tile == 'us') return this.resources.us.texture;
    else if(tile == 'uc') return this.resources.uc.texture;
    else if(tile == 'ow') return this.resources.ow.texture;
    else if(tile == 'bl') return this.resources.bl.texture;
    else if(tile == 'bm') return this.resources.bm.texture;
    else if(tile == 'bs') return this.resources.bs.texture;
    else if(tile == 'dl') return this.resources.dl.texture;
    else if(tile == 'dm') return this.resources.dm.texture;
    else if(tile == 'ds') return this.resources.ds.texture;
    else if(tile == 'sl') return this.resources.sl.texture;
    else if(tile == 'sm') return this.resources.sm.texture;
    else if(tile == 'ss') return this.resources.ss.texture;
    else return this.resources.p.texture;

  }

}
