package ro.jtonic.handson.springscala

import org.springframework.stereotype.Component
import javax.servlet._
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import ro.jtonic.handson.springscala.Headers._

@Component
class XClacksOverhead extends Filter {

  @throws[IOException]
  @throws[ServletException]
  def doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain): Unit = {
    val response = res.asInstanceOf[HttpServletResponse]
    response.setHeader(X_CLACKS_OVERHEAD, "GNU Terry Pratchett")
    chain.doFilter(req, res)
  }

  def destroy(): Unit = {
  }

  @throws[ServletException]
  def init(arg0: FilterConfig): Unit = {
  }
}
