package eu.brosbit.http

import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router

class MapRouter(vertx: Vertx, router:Router) extends BaseRouter(vertx, router):
  router.route(HttpMethod.GET, "/map").handler(ctx =>
    val res = ctx.response()
    res.end("empty")
  )

  router.route(HttpMethod.GET, "/").handler(ctx => {
    val res = ctx.response()
    //println("Start / page")
    vertx.fileSystem().readFile("index.html").onSuccess(file => {
      val data = file.toString("UTF-8")
      res.end(data)
    }).onFailure(e => {
      res.setStatusCode(406)
      res.end("error\n")
    })
  })

  createStateRoute("favicon.ico")
