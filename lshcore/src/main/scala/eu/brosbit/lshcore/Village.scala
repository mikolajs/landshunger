package eu.brosbit.lshcore
import eu.brosbit.lshcore.immovable.*
import eu.brosbit.lshcore.tiles.*
import eu.brosbit.lshcore.farm.*
import eu.brosbit.lshcore.items.{ItemList, Peasants}


class Village():
  var houses = 120
  var peasants = 5
  var nobles = 1
  var farmExp = 1.0
  var mineExp = 1.0
  var timberExp = 1.0
  var fishingExp = 1.0
  var farmTools = 0
  var mineTools = 0
  var timberTools = 0
  var fishTools = 0
  var turnTime = peasants*10
  val cows = Cows()
  val ship = Ship()
  val pigs = Pigs()
  val horses = Horses()

  val items:ItemList = ItemList()
