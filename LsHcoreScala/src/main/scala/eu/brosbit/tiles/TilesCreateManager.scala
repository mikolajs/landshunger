package eu.brosbit.tiles

import java.util.Scanner
import java.io.File
import java.io.IOException
import java.lang.reflect.Constructor

case class TileConstant(val aType:Byte, val lev:Byte, val fertility:Byte, val name:String) {
   override def toString() = 
    	s"name + t:  $aType l: $lev f: $fertility"
}

object TilesCreateManager {
  val tileConfigPath =	"configdata/tiledata.cfg"
  val packageName = "eu.brosbit.tiles."
  
   def getTile(className:String):Option[Tile] = {
    val fullClassName = packageName + className
    val tileOpt = try {
       var c:Constructor[_] = null
       Class.forName(fullClassName).getConstructors.find(con => {
         if(con.getParameterCount == 1) {c = con; true}
         else false
       })
       Some(c.newInstance(getTileConst(className)).asInstanceOf[Tile])
    } catch {
      case e:Exception => println("Error initialize while Tile: " + fullClassName +  " \n " + e.toString());None
    }
    tileOpt
  }
  
  private val tileConsts:Map[String, TileConstant] = getConstants
  
  private def getConstants  = {
    import scala.collection.immutable.TreeMap
    var tileMap = TreeMap[String, TileConstant]()
    val file = new File(tileConfigPath);
		var sc:Scanner = null;
		try {
			sc = new Scanner(file);
			var tc:TileConstant = null
			sc.nextLine()
			while (sc.hasNext()) {
				tc = new TileConstant(
						sc.nextByte(), 
						sc.nextByte(), 
						sc.nextByte(),
						sc.next())
				tileMap += (tc.name -> tc);
			  println(tc.toString());

			}
		} catch {
		  case e:IOException =>
			 println("Cant load file for config tiles")
			 
		} finally {
			if (sc != null)
				sc.close();
		}
		tileMap
  }
  
  private def getTileConst(className:String)={
    tileConsts(className)
  }
  
}



