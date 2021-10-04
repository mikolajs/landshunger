class MapWorld {
  constructor(grid, X, Y, container, resources){
    this.grid = grid;
    this.X = X;
    this.Y = Y;
    this.container = container;
    this.resources = resources;
    console.log("Start Map of World: " + this.X + " x " + this.Y);
    //let worldsMap = new WorldMap();
    //this.drawMap(worldsMap.worldTiles);
    this.worldData = new WorldMap();
    this.startX = 44;
    this.startY = 44;
      this.drawMap();
  }
  //// TODO: Implement
  drawMap(){
    //// TODO: Change direction x and y (must be the same as in worldmap test())
    // console.log(resources);
    let startX = this.startX;
    let startY = this.startY;

    let tile = "";
    for(let i = 0; i < this.X; i++){
      for(let j = 0; j < this.Y; j++){
        tile = this.worldData.worldTiles[startX+i][startY+j];
        const sprite = new PIXI.Sprite(this._getTileTexture(tile));
        sprite.anchor.set(0.5);
        const p = this.grid.getCenterOfPoolInPixels(i, j);
        sprite.x = p.x;
        sprite.y = p.y;
        this.container.addChild(sprite);
        //console.log("Title added at " + i + "," + j);
        //console.log(p);
      }
    }
  }

  scrollUp(){
    if(this.startX >= 2) this.startX -= 2;
    this.drawMap();
  }

  scrollDown(){
    if(this.startX <= 88) this.startX += 2;
    this.drawMap();
  }
  scrollLeft(){
    if(this.startY >= 2) this.startY -= 2;
    this.drawMap();
  }
  scrollRight(){
    if(this.startY <= 88) this.startY += 2;
    this.drawMap();
  }

  _getTileTexture(tile){
    if(tile == 'p') return this.resources.p.texture;
    else if(tile == 'w') return this.resources.w.texture;
    else if(tile == 'h') return this.resources.h.texture;
    else if(tile == 'm') return this.resources.m.texture;
    else return this.resources.p.texture;

  }

}
