package eu.brosbit.tiles

import java.util.Scanner
import java.io.File
import java.io.IOException
import java.lang.reflect.Constructor

import scala.collection.immutable.TreeMap

case class TileData(aType:Byte, level:Byte, fertility:Byte)

case class TileConstants(data:TreeMap[String, TileData])

object TilesCreateManager {
  val tileConfigPath =	"configdata/tiledata.cfg"

	private var tileMap = TreeMap[String, TileData]()

    val file = new File(tileConfigPath)
		var sc:Scanner = null;
		try {
			sc = new Scanner(file);
			sc.nextLine()
			while (sc.hasNext()) {
				val theName = sc.next()
				val td = TileData(
						sc.nextByte(), 
						sc.nextByte(), 
						sc.nextByte())
				tileMap += (theName -> td)
			}
		} catch {
		  case e:IOException =>
			 println("Cant load file for config tiles")
		} finally {
			if (sc != null)
				sc.close();
		}
  
  def getTileData(name:String) = {
    tileMap(name)
  }
  
}



