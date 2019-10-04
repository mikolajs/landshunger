package eu.brosbit

import scala.scalajs.js
import org.scalajs.dom
import dom.{ document, window }
import com.scalawarrior.scalajs.createjs._


object MainApp  {

  def main(args:Array[String]): Unit = {
    println("Starting 'hexfleet'...")

    val p = document.createElement("p")
    val text = document.createTextNode("Hello!")
    p.appendChild(text)
    document.body.appendChild(p)
    val c =  document.createElement("canvas")
    c.id = "demoCanvas"
    document.body.appendChild(c)
    val stage = new Stage("demoCanvas")
    val w = 1200 //stage.canvas.width
    val h = 720 //stage.canvas.height


    val circle = new Shape()
    circle.graphics.beginFill("DeepSkyBlue").drawCircle(0, 0, 50)
    circle.x = 100
    circle.y = 100
    stage.addChild(circle)
    stage.update();
  }

}
