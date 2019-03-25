// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Ospiegel/Development/emergeInterview/app/sound_collector/conf/routes
// @DATE:Mon Mar 25 11:27:54 IST 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:9
package controllers {

  // @LINE:13
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def api_post(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "sound")
    }
  
    // @LINE:16
    def api_query(queryType:String = "countAll", startTime:Long = 0, endTime:Long = 0): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "query/" + play.core.routing.queryString(List(if(queryType == "countAll") None else Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("queryType", queryType)), if(startTime == 0) None else Some(implicitly[play.api.mvc.QueryStringBindable[Long]].unbind("startTime", startTime)), if(endTime == 0) None else Some(implicitly[play.api.mvc.QueryStringBindable[Long]].unbind("endTime", endTime)))))
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
