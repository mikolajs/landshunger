
class Deposit(aType:String):
  var step = 0
  val (area, depth, aParam, bParam) = randomDeposit()
  println(s"area $area, depth: $depth, A: $aParam, B: $bParam")
  import Deposit.*
  var canDig = false
  var deckWork = 0
  var deckValue = 0.0
  val woodPerWork = woodConst*2.0/workConst
  var depDiged = 0.0
  var woodGather = 0.0
  println(s"Wood Per Work = $woodPerWork")
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
        mkStartBuild()
      (deposit, work)

  def canBuildDeck():Boolean =  !canDig && step < depth

  //return used work and used wood
  def buildDeck(work:Int, wood:Int) =
    if !canBuildDeck() then
      (0, 0)
    else
      val remainWork = if deckWork < work then deckWork else work
      val needWood = scala.math.ceil(remainWork*woodPerWork).toInt
      val rWood:Double = needWood.toDouble - remainWork*woodPerWork
      woodGather += rWood
      val gw = woodGather.toInt
      woodGather -= gw
      val avWood = wood + gw
      if avWood >= needWood then
        deckWork -= remainWork
        if deckWork == 0 then mkStartDig()
        (remainWork, needWood-gw)
      else
        val canWork = (avWood/woodPerWork).toInt
        deckWork -= canWork
        //if deckWork < 0 then println("ERROR: deckWork below 0 !!!!!!!!!!")
        if deckWork <= 0 then
          deckWork = 0
          mkStartDig()
        (canWork, wood)

  def maxPeopleWork = area

  private def mkStartDig() =
    canDig = true
    deckValue = (-aParam*step*step + bParam*step).toDouble
    deckWork = area*workConst

  private def mkStartBuild() =
    canDig = false
    deckValue = 0
    deckWork = area*workConst/2
    step += 1

  private def randomDeposit() =
    import scala.util.Random
    val rand = Random()
    val areaTmp  = rand.nextInt(areaMedium) + areaMedium/2
    val depthTmp = rand.nextInt(depthMedium) + depthMedium/2
    val Y = richMedium + rand.nextInt(richMedium/2)
    val BB:Double = 4.0*Y/depthTmp.toDouble
    val AA:Double = BB/depthTmp
    println(s"orginal depth $depthTmp, rich: $Y")
    step = (depthTmp*0.09).toInt
    (areaTmp, depthTmp - (step*0.6).toInt, AA, BB)

object Deposit:
  val workConst = 20 //hom many to build(half) or dig in 1 unit of area to step
  val woodConst = 8 //how many to build 1 unit of area in step
  val areaMedium = 50
  val depthMedium = 100
  val richMedium = 400


def test(dep:Deposit):Unit =
  val Fx = (((dep.bParam*dep.bParam*dep.bParam)/(dep.aParam*dep.aParam))/6.0).toInt
  val works = Deposit.workConst*dep.area*dep.depth
  val timber = dep.area*Deposit.woodConst*dep.depth
  println(s"minerals: $Fx works: $works, timber: $timber")

def main():Unit =
  val mine = Deposit("iron")
  var iron = 0
  var wood = 0
  var workD = 0
  var workB = 0
  var lastIron = 0
  var lastWood = 0
  var lastWD = 0
  var lastWB = 0
  import scala.util.Random
  val r = Random()
  while mine.step < mine.depth do
    //println(s"step: ${mine.step}")
    while !mine.canDig do
      val woodS = r.nextInt(20)+1
      val workS = r.nextInt(400)+20
      val (rWork, rWood) = mine.buildDeck(workS, woodS)
      wood += rWood
      workB += rWork
      //println(s"build deck $wood, $workB")
    while mine.canDig do
      val workS = r.nextInt(500)+20
      val (dep, rWork) = mine.mkDig(workS)
      workD += rWork
      iron += dep
    val dIron = iron - lastIron
    val dwb = workB - lastWB
    val dwd = workD - lastWD
    val dWood = wood - lastWood
      //println(s"mk dig dep=$dep, work=$rWork")
    println(s"step ${mine.step} iron values $dIron works: dig $dwd build $dwb, timber: $dWood")
    lastWood = wood
    lastIron = iron
    lastWB = workB
    lastWD = workD
  println(s"all iron values $iron works: dig $workD build $workB, timber: $wood")
  test(mine)

