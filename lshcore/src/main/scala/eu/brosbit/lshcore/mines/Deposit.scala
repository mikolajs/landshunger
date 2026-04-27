//area - ile ludzi może na raz pracować
//depth - jak głęboko można wejść do kopalni
class Deposit(aType:String):
  val (area, depth, aParam, bParam) = randomDeposit()
  println(s"area $area, depth: $depth, A: $aParam, B: $bParam")
  val workConst = 100
  val woodConst = 0.3 // how many wood per unit work
  var canDig = false
  var deckWork = 0
  var deckValue = 0.0
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
      canDig = false
      deckSize = 0
      (deposit, work - workDeck)
    else
      val deposit = deckValue*(work/workDeck)
      workDeck -= work
      if workDeck == 0 then 
        canDig = false
        deckSize = 0
      (deposit, 0)

  def canBuildDeck():Boolean =  !canDig && step < depth

  def buildDeck(work:Int, wood:Int) =
    if !canBuildDeck() then (work, wood)
    else
      val availibleWoodWork = scala.math.round(wood / woodConst).toInt
      val doWork = if availibleWoodWork >= work then work else availibleWoodWork
      if doWork >= deckWork then
        wood -= scala.math.ceil(deckWork*woodConst).toInt
        work -= deckWork
        deckWork = 0
        mkStartDig()
        (work, wood)
      else
        deckWork -= doWork
        work -= doWork
        wood -= scala.math.ceil(doWork*woodConst).toInt
        (work, wood)
        

      
  private def mkStartDig() =
    canDig = true
    val deckRich = (-aParam*step*step + bParam*step).toDouble
    deckWork = area*workConst
    deckValue = deckRich/deckWork.toDouble
    
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
  

main()

