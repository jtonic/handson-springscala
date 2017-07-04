package ro.jtonic.handson.springscala

import java.util
import javax.servlet.DispatcherType

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.{Bean, Configuration}

/**
  * Created by Antonel Ernest Pazargic on 04/07/2017.
  *
  * @author Antonel Ernest Pazargic
  */
@Configuration
class WebConfig {

  @Bean def clacksOverheadFilter(filter: XClacksOverhead): FilterRegistrationBean = {
    val registration = new FilterRegistrationBean
    registration.setFilter(filter)
    registration.setDispatcherTypes(util.EnumSet.allOf(classOf[DispatcherType]))
    registration.addUrlPatterns("/*")
    registration
  }

}
