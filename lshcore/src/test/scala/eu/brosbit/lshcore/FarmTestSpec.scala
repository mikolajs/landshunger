package eu.brosbit.lshcore
import eu.brosbit.lshcore.immovable.*
import eu.brosbit.lshcore.tiles.*
import eu.brosbit.lshcore.*
import eu.brosbit.lshcore.items.{ItemList, Peasants}
import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec


class FarmTestSpec extends AnyFlatSpec with Matchers:
    val items = ItemList()
    val tiles = Array(Steppe(), Steppe(), Steppe(), Humus(), Humus(), Humus(), Plain(), Plain(), Plain(), Plain(), Plain(), Plain(), Hill(), Hill(), Hill(), Hill(), Mountain(), Mountain())
    val fields = Array[ImmovableObject](Grass(), Grass(), Grass(), Grass(), Wheat(), Wheat(), Wheat(), Wheat(), Wheat(), Oat(), Grass(), Grass(), Grass(), Oat(), Oat(), Oat(), Wasteland(), Wasteland())
    val peasants = Peasants() 
    peasants.n = 80
    val cows = Cows()
    cows.maxN = 50
    val ship = Ship()
    ship.maxN = 66
    val goats = Goats()
    goats.maxN = 35
    val pigs = Pigs()
    pigs.maxN = 55
    items.forage = 190000
    items.corns = 30000
    
    var lastTime = 0
    for i <- 1 to 500 do
        var harvGrass = 0
        var harvCorn = 0
        peasants.workTime = peasants.n*120
        //last = forage
        fields.foreach(f => f.nextDay())
        println(fields.map(f =>f.log).mkString("//"))
        fields.foreach {
          case gr: Grass if gr.getBio > gr.obj.maxBio / 3 => 
              harvGrass += gr.harvest(items, peasants)
          case gr: Grass =>
          case pl: Plant => harvCorn += pl.harvest(items, peasants)
          case _ =>
        }
        items.forage = cows.eat(items.forage)
        items.cheesCow += cows.getDayProduct
        items.forage = ship.eat(items.forage)
        items.chees += ship.getDayProduct
        items.forage = goats.eat(items.forage)
        items.chees += goats.getDayProduct
        items.corns = pigs.eat(items.corns)
        if i % 5 == 0 then
          items.wool += ship.getWeekProduct
          cows.reproduce()
          ship.reproduce()
          goats.reproduce()
          pigs.reproduce()
          cows.mkSlaughter(items)
          ship.mkSlaughter(items)
          goats.mkSlaughter(items)
          pigs.mkSlaughter(items)
          
        println(s"day: $i ===================================" )
        println(items.showInfo)
        println(s"Cows ${cows.n}, Ship: ${ship.n}, Goats: ${goats.n}, Pigs: ${pigs.n}")
        println(s"Young:: Cows ${cows.y}, Ship: ${ship.y}, Goats: ${goats.y}, Pigs: ${pigs.y}")
        println("woking time: " + peasants.workTime)
        println(s"harvest corn: $harvCorn, grass: $harvGrass")
    println(items.showInfo)
    println(s"Cows ${cows.n}, Ship: ${ship.n}, Goats: ${goats.n}, Pigs: ${pigs.n}")
    println(s"Young:: Cows ${cows.y}, Ship: ${ship.y}, Goats: ${goats.y}, Pigs: ${pigs.y}")
    "Farm " should "not be empty animals" in {
      (goats.n + cows.n + pigs.n + ship.n) should be > 100
    }
    
