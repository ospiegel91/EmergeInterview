// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Ospiegel/Development/emergeInterview/app/sound_collector/conf/routes
// @DATE:Sun Mar 24 23:31:56 IST 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
