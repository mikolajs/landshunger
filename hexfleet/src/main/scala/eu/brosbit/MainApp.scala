package eu.brosbit

import scala.scalajs.js
import org.scalajs.dom
import dom.{ document, window }
import com.scalawarrior.scalajs.createjs._


object MainApp  {

  var started = true
  var time = 0L

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


    val background = new Shape();
    background.graphics.beginFill("#444444")
    background.graphics.drawRect(0,0, w, h)
    stage.addChild(background)
    Ticker.setFPS(60)
    Ticker.addEventListener("tick", stage)
    stage.addEventListener("tick", handleTick)
    stage.update();
  }

  def handleTick(event:Any) =  {
    if (!(event.asInstanceOf[Event].paused) && started) {
       time += 1
      if(time % 600 == 0) println(time)
    }
    true
  }

}
