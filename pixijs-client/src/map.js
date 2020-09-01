class MapWorld {
  constructor(grid, X, Y, container, resources){
    this.grid = grid;
    this.X = X;
    this.Y = Y;
    this.container = container;
    this.resources = resources;
    console.log("Start Map of World: " + this.X + " x " + this.Y);
    this.drawMap(null);
  }
  //// TODO: Implement
  drawMap(mapData){
    //const texture = PIXI.Texture.from('img/water_120x72.png');
    // console.log(resources);
    for(let i = 0; i < this.X; i++){
      for(let j = -1; j < this.Y; j++){
        const sprite = new PIXI.Sprite(this.resources.w.texture);
        sprite.anchor.set(0.5);
        const p = this.grid.getCenterOfPoolInPixels(i, j);
        sprite.x = p.x;
        sprite.y = p.y;
        this.container.addChild(sprite);
        console.log("Title added at " + i + "," + j);
        console.log(p);
      }
    }
  }

}
