
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, FlatSpec}
import eu.brosbit.HexMap

class HexMapSpec extends FlatSpec with Matchers {

      behavior of "Map"

        val arrayOfMap = HexMap.hexMap.map

      it should "have all map field set to false" in {
        arrayOfMap.foreach(_.foreach(_.shouldEqual(false)))
	}
      it should "set mined to empty field" in { 
        HexMap.hexMap.setMined(4, 8) shouldEqual(true)
      }
      it should "be mined after set" in {
        HexMap.hexMap.isMined(4, 8) shouldEqual(true)
      }
      it should "return false if hex is not mined" in {
        HexMap.hexMap.isMined(8,4) shouldEqual(false)
      }
}
