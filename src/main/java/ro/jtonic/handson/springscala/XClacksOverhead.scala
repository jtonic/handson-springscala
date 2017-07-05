package ro.jtonic.handson.springscala

import javax.servlet.FilterChain
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.util.AntPathMatcher

@Component
class XClacksOverhead extends OncePerRequestFilter {

  val excludePatterns = List("/keep*", "/swagger*", "/v2/api-docs*")

  val pathMatcher = new AntPathMatcher

  override def doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain): Unit = {
    response.setHeader(Headers.X_ACCESS_TOKEN, "ACCESS TOKEN ASDFK;AJSDFLASJDFADSF134123412ADASDFA")
  }

  override def shouldNotFilter(request: HttpServletRequest) =
    excludePatterns.exists(pathMatcher.`match`(_, request.getServletPath))
}
