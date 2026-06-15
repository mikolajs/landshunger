//area - ile ludzi może na raz115 pracować
//depth - jak głęboko można wejść do kopalni
class Deposit(aType:String, reachConst:Double):
  val (area, depth, aParam, bParam) = randomDeposit()
  println(s"area $area, depth: $depth, A: $aParam, B: $bParam")
  val workConst = 100
  val woodConst = 0.15 // how many wood per unit work
  var canDig = false
  var deckWork = 0
  var deckValue = 0.0 // how many deposit on unit deck work
  var step = 0
  def mkDig(work:Int) = 
    if step > depth || !canDig then
      (0, work)
    else if  work >= deckWork then
      var workR = work
      val deposit = scala.math.round(deckValue*work).toInt
      deckValue = 0
      workR -= deckWork 
      deckWork = area*workConst
      canDig = false
      (deposit, workR)
    else
      val deposit = scala.math.round(deckValue*work).toInt
      deckWork -= work
      (deposit, 0)

  def canBuildDeck():Boolean =  !canDig && step < depth

  def buildDeck(work:Int, wood:Int) =
    if !canBuildDeck() then (work, wood)
    else
      val availibleWoodWork = scala.math.round(wood / woodConst).toInt
      val doWork = if availibleWoodWork >= work then work else availibleWoodWork
      var aWork = work
      var aWood = wood
      if doWork >= deckWork then
        aWood -= scala.math.round(deckWork*woodConst).toInt
        aWork -= deckWork
        mkStartDig()
        (aWork, aWood)
      else
        deckWork -= doWork
        aWork -= doWork
        aWood -= scala.math.round(doWork*woodConst).toInt
        (aWork, aWood)
        
  def maxPeopleWork = area
      
  private def mkStartDig() =
    canDig = true
    val deckRich = (-aParam*step*step + bParam*step).toDouble
    deckWork = area*workConst
    deckValue = deckRich/deckWork.toDouble*reachConst
    step += 1
    
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
  val mine = Deposit("iron", 0.95)
  var iron = 0
  var workB = 0
  var workD = 0
  var wood = 0
  import scala.util.Random
  val rand = Random()
  var buildRound = 0
  var digRound = 0
  while mine.step < mine.depth do
    if mine.canDig then
      val w = rand.nextInt(100) + 10
      val (ir, wr) = mine.mkDig(w)
      iron += ir
      workD += (w -wr)
      digRound += 1
    else
      val wr = rand.nextInt(100) + 10
      val wo = rand.nextInt(20) + 2
      val (workR, woodR) = mine.buildDeck(wr, wo)
      workB += (wr - workR)
      wood += (wo - woodR)
      buildRound += 1
  println(s"all iron procuce $iron, wood used $wood, work for build did $workB, work for dig $workD")
  println(s"build rounds $buildRound, dig rounds $digRound")
  

//main()

