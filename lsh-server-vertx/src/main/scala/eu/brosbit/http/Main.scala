package eu.brosbit.http

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import eu.brosbit.lscore.Game

object Main:
  val port = 9999
  val ipHost = "127.0.0.1"
  val SIZE = 30
  val game = Game(SIZE)
  def main(args:Array[String]):Unit =
    val vertx = Vertx.vertx()
    val router = Router.router(vertx)
    val mapRouter = MapRouter(vertx, router)
    val server = vertx.createHttpServer()
    server.requestHandler(
      router
    ).listen(port, ipHost).onSuccess(server => println(s"Run server: http://$ipHost:$port")).onFailure( error =>
      println(s"Cannot set server ${error.toString}")
      System.exit(1)
    )