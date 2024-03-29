package eu.brosbit

import io.vertx.core.Vertx
import eu.brosbit.hexlib.*
import io.vertx.core.file.OpenOptions

object Main {
    def main(args:Array[String]):Unit =
        //hello
        val gen = MapGenerator(100)
        val vertx = Vertx.vertx()
        val fs = vertx.fileSystem()
        val afile = fs.openBlocking("index.html", OpenOptions())
        println(afile.getReadLength)
        vertx.createHttpServer().requestHandler(req =>  {
            req.response()
              .putHeader("content-type", "application/json")
              .putHeader("Access-Control-Allow-Origin", "*")
              .end(gen.getMapJson())
        }).listen(8008, "localhost").onSuccess(server => {
            println("Server: " + server.actualPort())})

    
    def hello: Unit =
        println("Hello world!")
        println(msg)

    def msg = "I was compiled by Scala 3. :)"
    
}

