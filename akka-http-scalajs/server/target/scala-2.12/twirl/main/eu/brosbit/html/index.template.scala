
package eu.brosbit.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Akka HTTP with Scala.js")/*3.33*/ {_display_(Seq[Any](format.raw/*3.35*/("""
    """),format.raw/*4.5*/("""<h2>Akka HTTP and Scala.js share a same message</h2>
    <ul>
        <li>Akka HTTP shouts out: <em>"""),_display_(/*6.40*/message),format.raw/*6.47*/("""</em></li>
        <li>Scala.js shouts out: <em id="scalajsShoutOut"></em></li>
    </ul>
""")))}),format.raw/*9.2*/("""
"""))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon May 06 17:56:04 CEST 2019
                  SOURCE: /home/ms/Programming/landshunger/akka-http-scalajs/server/src/main/twirl/eu/brosbit/index.scala.html
                  HASH: fac56551d4363ec4a085d1743430f97d1466059d
                  MATRIX: 571->1|682->19|709->21|748->52|787->54|818->59|945->160|972->167|1092->258
                  LINES: 14->1|19->2|20->3|20->3|20->3|21->4|23->6|23->6|26->9
                  -- GENERATED --
              */
          