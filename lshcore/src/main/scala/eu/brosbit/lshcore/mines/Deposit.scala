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
  var depDiged = 0.0
  var step = 0
  def mkDig(work:Int):(Int, Int) = 
    if step > depth then
      (0, work)
    else if  work > deckWork then
      var workR = work
      depDiged += deckValue 
      val deposit = depDiged.toInt 
      depDiged -= depDiged.toInt
      deckValue = 0.0
      workR -= deckWork 
      deckWork= 0
      canDig = false
      (deposit, work - deckWork)
    else
      val deposit = deckValue*(work/deckWork)
      deckWork -= work
      if deckWork == 0 then 
        canDig = false
        deckValue = 0
      (deposit.toInt, 0)

  def canBuildDeck():Boolean =  !canDig && step < depth

  def buildDeck(work:Int, wood:Int) =
    if !canBuildDeck() then (work, wood)
    else
      val availibleWoodWork = scala.math.round(wood / woodConst).toInt
      val doWork = if availibleWoodWork >= work then work else availibleWoodWork
      var woodR = wood
      if doWork >= deckWork then
        woodR -= scala.math.ceil(deckWork*woodConst).toInt
        val workR = deckWork
        deckWork = 0
        mkStartDig()
        (work-workR, woodR)
      else
        deckWork -= doWork
        woodR -= scala.math.ceil(doWork*woodConst).toInt
        (work-doWork, woodR)
        

      
  private def mkStartDig() =
    canDig = true
    deckValue = (-aParam*step*step + bParam*step).toDouble
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



@main
def main():Unit = 
  val mine = Deposit("iron")
  var iron = 0
  var wood = 0 
  var work = 0
  import scala.util.Random
  val r = Random()
  while mine.step < mine.depth do
    while !mine.canDig do
      val woodS = r.nextInt(40)+10
      val workS = r.nextInt(200)+100
      val (rWork, rWood) = mine.buildDeck(workS, woodS)
      wood += (woodS - rWood)
      work += (workS - rWork)
      println(s"build deck $wood, $work, ${mine.step}")
    while mine.canDig do
      val workS = r.nextInt(200)+100
      val (dep, rWork) = mine.mkDig(workS)
      work += (workS - rWork)
      iron += dep.toInt
      println(s"mk dig $iron, $work")

  println(s"all iron values $iron works: $work, timber: $wood")
