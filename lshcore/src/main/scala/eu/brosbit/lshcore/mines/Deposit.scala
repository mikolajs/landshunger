//area - ile ludzi może na raz pracować
//depth - jak głęboko można wejść do kopalni
class Deposit(aType:String):
  val (area, depth, aParam, bParam) = randomDeposit()
  println(s"area $area, depth: $depth, A: $aParam, B: $bParam")
  val workConst = 100
  var workDeck = 0
  var deckValue = 0
  var step = 0
  def mkDig(work:Int) = 
    if step > depth then
      (0, work)
    else if  work > workDeck then
      var workR = work
      val deposit = deckValue
      deckValue = 0
      workR -= workDeck
      workDeck = 0
      (deposit, work - workDeck)
    else
      val deposit = deckValue*(work/workDeck)
      workDeck -= work
      (deposit, 0)

  def canBuildDeck():Boolean = workDeck == 0 && step < depth

  def buildDeck(work:Int, wood:Int) =
    if step >= depth then
      (work, wood)
    else
      step += 1
      workDeck = area*workConst/10
      deckValue = (-aParam*step*step + bParam*step).toInt
      println(s"$step, $deckValue")
      (work, wood) 

  private def randomDeposit() = 
    import scala.util.Random
    val rand = Random()
    val areaTmp  = rand.nextInt(20) + 40
    val depthTmp = rand.nextInt(60) + 90
    val Y = 400 + rand.nextInt(200)
    val BB:Double = 4.0*Y/depthTmp
    val AA:Double = BB/depthTmp
    (areaTmp, depthTmp, AA, BB) 



@main
def main():Unit = 
  val mine = Deposit("iron")
  var iron = 0
  while mine.step < mine.depth do
    mine.buildDeck(1000, 100)
    iron += mine.deckValue
  println(s"all iron values $iron")
  

