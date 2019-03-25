// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Ospiegel/Development/emergeInterview/app/sound_collector/conf/routes
// @DATE:Mon Mar 25 11:20:22 IST 2019


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
