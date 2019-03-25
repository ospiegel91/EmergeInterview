// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Ospiegel/Development/emergeInterview/app/sound_collector/conf/routes
// @DATE:Mon Mar 25 11:20:22 IST 2019

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:9
  Assets_0: controllers.Assets,
  // @LINE:13
  HomeController_1: controllers.HomeController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:9
    Assets_0: controllers.Assets,
    // @LINE:13
    HomeController_1: controllers.HomeController
  ) = this(errorHandler, Assets_0, HomeController_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Assets_0, HomeController_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sound""", """controllers.HomeController.api_post"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """query/""", """controllers.HomeController.api_query(queryType:String ?= "countAll", startTime:Integer ?= 0, endTime:Integer ?= 0)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:9
  private[this] lazy val controllers_Assets_versioned0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned0_invoker = createInvoker(
    Assets_0.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_HomeController_api_post1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sound")))
  )
  private[this] lazy val controllers_HomeController_api_post1_invoker = createInvoker(
    HomeController_1.api_post,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "api_post",
      Nil,
      "POST",
      this.prefix + """sound""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_HomeController_api_query2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("query/")))
  )
  private[this] lazy val controllers_HomeController_api_query2_invoker = createInvoker(
    HomeController_1.api_query(fakeValue[String], fakeValue[Integer], fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "api_query",
      Seq(classOf[String], classOf[Integer], classOf[Integer]),
      "GET",
      this.prefix + """query/""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:9
    case controllers_Assets_versioned0_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned0_invoker.call(Assets_0.versioned(path, file))
      }
  
    // @LINE:13
    case controllers_HomeController_api_post1_route(params@_) =>
      call { 
        controllers_HomeController_api_post1_invoker.call(HomeController_1.api_post)
      }
  
    // @LINE:16
    case controllers_HomeController_api_query2_route(params@_) =>
      call(params.fromQuery[String]("queryType", Some("countAll")), params.fromQuery[Integer]("startTime", Some(0)), params.fromQuery[Integer]("endTime", Some(0))) { (queryType, startTime, endTime) =>
        controllers_HomeController_api_query2_invoker.call(HomeController_1.api_query(queryType, startTime, endTime))
      }
  }
}
