// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Ospiegel/Development/emergeInterview/app/sound_collector/conf/routes
// @DATE:Sun Mar 24 23:31:56 IST 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def api_query(queryType:String = "countAll", startTime:Integer = 0, endTime:Integer = 0): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "query/" + play.core.routing.queryString(List(if(queryType == "countAll") None else Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("queryType", queryType)), if(startTime == 0) None else Some(implicitly[play.api.mvc.QueryStringBindable[Integer]].unbind("startTime", startTime)), if(endTime == 0) None else Some(implicitly[play.api.mvc.QueryStringBindable[Integer]].unbind("endTime", endTime)))))
    }
  
    // @LINE:13
    def api_post(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "sound")
    }
  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:9
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
