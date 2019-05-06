
package eu.brosbit.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html>

<html>
  <head>
    <title>"""),_display_(/*7.13*/title),format.raw/*7.18*/("""</title>
  </head>
  <body>
   """),_display_(/*10.5*/content),format.raw/*10.12*/("""
   """),_display_(/*11.5*/scalajs/*11.12*/.html.scripts("client", name => s"/assets/$name", name => getClass.getResource(s"/public/$name") != null)),format.raw/*11.117*/("""
  """),format.raw/*12.3*/("""</body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon May 06 17:56:04 CEST 2019
                  SOURCE: /home/ms/Programming/landshunger/akka-http-scalajs/server/src/main/twirl/eu/brosbit/main.scala.html
                  HASH: 615c92f38297e110358807e3fda7e4a67460193c
                  MATRIX: 575->1|699->32|726->33|797->78|822->83|880->115|908->122|939->127|955->134|1082->239|1112->242
                  LINES: 14->1|19->2|20->3|24->7|24->7|27->10|27->10|28->11|28->11|28->11|29->12
                  -- GENERATED --
              */
          