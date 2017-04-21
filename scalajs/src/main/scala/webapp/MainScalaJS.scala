package webapp

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document

class MainScalaJS  extends  JSApp {

    def main(): Unit = {
      println("Hello world!")
      appendParagraph(document.body, "Witaj w świecie Scala.JS")
    }
    private def appendParagraph(targetNode: dom.Node, text: String) : Unit = {
      val parNode = document.createElement("p")
      val textNode = document.createTextNode(text)
      parNode.appendChild(textNode)
      targetNode.appendChild(parNode)
    }
}
