import scala.util.Random
val rand = Random()


class Fishing:
  val fishPools = Array.ofDim[Int](4).map(_ => rand.nextInt(30))
  var people = 25
  val fishExp = 1.2
  val boat = 2
  var workingPools = List() 
  def growFish =
    var howM = 0
    for i <- 0 until fishPools.length do
      if fishPools(i) > 0 then 
        fishPools(i) = (fishPools(i)*1.2).toInt
        howM += 1
    if howM < fishPools/2 then
      val r = rand.nextInt(fishPools.length)
      if fishPools(r) == 0 then fishPools(i) = 10+rand.nextInt(20)

  def howManyGoodFisheries = fishPools.filter(_ > 20).map(_ => 1).sum 
  
  def choiseBestPool = 
    (0 until fishPools.length).zip(fishPools).reduce((a, b) => 
        if a._2 > b._2 then a else b)._1

  def nextRound =
    var fishCaught  = 0
    
      
@main
def main():Unit = 
  val fishing = Fishing()
  for day <- 1 to 10 do
    for minute <- 1 to 100 do
      
