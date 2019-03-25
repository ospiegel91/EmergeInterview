// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Ospiegel/Development/emergeInterview/app/sound_collector/conf/routes
// @DATE:Sun Mar 24 23:31:56 IST 2019

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def api_query: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.api_query",
      """
        function(queryType0,startTime1,endTime2) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "query/" + _qS([(queryType0 == null ? null : (""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("queryType", queryType0)), (startTime1 == null ? null : (""" + implicitly[play.api.mvc.QueryStringBindable[Integer]].javascriptUnbind + """)("startTime", startTime1)), (endTime2 == null ? null : (""" + implicitly[play.api.mvc.QueryStringBindable[Integer]].javascriptUnbind + """)("endTime", endTime2))])})
        }
      """
    )
  
    // @LINE:13
    def api_post: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.api_post",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sound"})
        }
      """
    )
  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:9
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
