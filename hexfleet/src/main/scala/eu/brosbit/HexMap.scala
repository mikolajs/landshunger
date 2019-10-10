package eu.brosbit


object HexMap {

  val hexMap = new MapData(30, 30)
}


class MapData(val row:Int, val col:Int) {
  val map =Array.ofDim[Boolean](row, col)
  for{ 
    i <- 0 until row
    j <- 0 until col } 
    map(i)(j) = false

  def isMined(x:Int, y:Int) = map(x)(y)
  def setMined(x:Int, y:Int) = if(isMined(x,y)) false else { map(x)(y) = true; true }
}
