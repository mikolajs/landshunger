package eu.brosbit.lshcore
import eu.brosbit.lshcore.immovable.*
import eu.brosbit.lshcore.tiles.*
//import eu.brosbit.lshcore.*
import eu.brosbit.lshcore.farm.*
import eu.brosbit.lshcore.items.{ItemList, Peasants}
import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec


class GrowCropsSpec extends AnyFlatSpec with Matchers:
    val items = ItemList()
    val tiles = Array(Steppe(), Steppe(), Steppe(), Humus(), Humus(), Humus(), Plain(), Plain(), Plain(), Plain(), Plain(), Plain(), Hill(), Hill(), Hill(), Hill(), Mountain(), Mountain())
    val fields = Array[ImmovableObject](Grass(), Grass(), Grass(), Grass(), Wheat(), Wheat(), Rye(), Rye(), Rye(), Clover(), Grass(), Grass(), Grass(), Oat(), Oat(), Oat(), Wasteland(), Wasteland())
    val peasants = Peasants() 
    peasants.n = 80
    items.forage = 0
    items.corns = 0
    

    var lastTime = 0
    //two years
    for i <- 1 to 120 do
        var harvForage= 0
        var harvCorn = 0
        peasants.workTime = peasants.n*120
        //last = forage
        fields.foreach(f => f.nextDay())
        println(fields.map(f =>f.log).mkString("/\t/"))
        fields.foreach {
          case wh: Wheat => harvCorn += wh.harvest(items, peasants)
          case ry: Rye => harvCorn += ry.harvest(items, peasants)
          case oa: Oat => harvCorn += oa.harvest(items, peasants)
          case cl: Clover if cl.getBio > cl.obj.maxBio / 3 => 
            harvForage += cl.harvest(items, peasants)
          case gr: Grass if gr.getBio > gr.obj.maxBio / 3 => 
            harvForage += gr.harvest(items, peasants)
          case _ =>
        }
          
        println(s"day: $i ===================================" )
        println(items.showInfo)
        println("woking time: " + peasants.workTime)
        println(s"harvest corn: $harvCorn, grass: $harvForage")
    "Farm " should "have enough corn" in {
      (items.corns) should be > 1000
    }
    it should "have enough forage" in {
      items.forage should be > 1000
    }
    
