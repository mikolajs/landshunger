//area - ile ludzi może na raz115 pracować
//depth - jak głęboko można wejść do kopalni
class Deposit(aType:String, reachConst:Double):
  val (area, depth, aParam, bParam) = randomDeposit()
  println(s"area $area, depth: $depth, A: $aParam, B: $bParam")
  import Deposit.*
  var canDig = false
  var deckWork = 0
  var deckValue = 0.0
  var depDiged = 0.0
  var woodGather = 0
  var step = 0
  mkStartBuild()
  //return diged deposit and used work
  def mkDig(work:Int):(Int, Int) = 
    if step > depth then
      (0, work)
    else if  work > deckWork then
      depDiged += deckValue 
      val deposit = depDiged.toInt 
      depDiged -= depDiged.toInt
      val workR = deckWork 
      mkStartBuild()
      (deposit, workR )
    else
      val dv = deckValue*(work.toDouble/deckWork.toDouble)
      //print(s"dv = $dv, deckValue = $deckValue")
      depDiged += dv
      deckValue -= dv
      val deposit = depDiged.toInt
      depDiged -= deposit
      deckWork -= work
      if deckWork == 0 then 
        canDig = false
        deckValue = 0
      (deposit, work)

  def canBuildDeck():Boolean =  !canDig && step < depth
  
  //return used work and used wood
  def buildDeck(work:Int, wood:Int) =
    if !canBuildDeck() then (0, 0)
    else
      val availibleWoodWork = scala.math.floor(wood / woodConst).toInt
      val doWork = if availibleWoodWork >= work then work else availibleWoodWork
      //println(s"doWork  = $doWork, $work, $wood, $deckWork")
      if doWork >= deckWork then
        val woodR = scala.math.floor(deckWork*woodConst).toInt
        val workR = deckWork
        deckWork = 0
        mkStartDig()
        (workR, woodR)
      else
        deckWork -= doWork
        val woodR = scala.math.floor(doWork*woodConst).toInt
        (doWork, woodR)
        
  def maxPeopleWork = area
      
  private def mkStartDig() =
    canDig = true
    deckValue = (-aParam*step*step + bParam*step).toDouble
    deckWork = area*workConst

  private def mkStartBuild() =
    canDig = false
    deckValue = 0
    deckWork = area*workConst 
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

object Deposit:
  val workConst = 20
  val woodConst = 0.15 // how many wood per unit work !change after change workConst

/*
@main
def main():Unit = 
  val mine = Deposit("iron", 0.95)
  var iron = 0
  var wood = 0 
  var workD = 0
  var workB = 0
  import scala.util.Random
  val r = Random()
  while mine.step < mine.depth do
    println(s"step: ${mine.step}")
    while !mine.canDig do
      val woodS = r.nextInt(20)+10
      val workS = r.nextInt(100)+500
      val (rWork, rWood) = mine.buildDeck(workS, woodS)
      wood += rWood
      workB += rWork
      println(s"build deck $wood, $workB")
    while mine.canDig do
      val workS = r.nextInt(100)+50
      val (dep, rWork) = mine.mkDig(workS)
      workD += rWork
      iron += dep
      //println(s"mk dig dep=$dep, work=$rWork")

  println(s"all iron values $iron works: dig $workD build $workB, timber: $wood")
*/
