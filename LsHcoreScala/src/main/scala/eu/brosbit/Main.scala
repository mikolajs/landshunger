package eu.brosbit




object Main {
  //
 
  def main(args: Array[String]): Unit = {
    val map = new TheMap(20)
    map.createMap
    val mapArray = map.getMap
    val statistics = new Statistics(mapArray)
    val woods = new Woods(mapArray)
    val grasses = new Grasses(mapArray)
    grasses.nextDay()
    woods.nexDay()
    for(i <- 1 to 50){
      println(s"Dzień $i")
      //map.printTiles
      statistics.countForests
      statistics.countHPOfForest
      statistics.countGrasses
      statistics.countHPOGrasses
      Thread.sleep(1000)
      map.nextDay()
      woods.nexDay()
      grasses.nextDay()
    }

  }
  
}


