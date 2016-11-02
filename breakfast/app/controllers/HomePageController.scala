package controllers

import javax.inject._

import org.joda.time.DateTime
import play.api._
import play.api.mvc._

trait TimeGreetingService {
  def greeting : String
}

object RealTimeGreeterService extends TimeGreetingService {
  def greeting = {
    if (DateTime.now.hourOfDay().get < 12) {
      "Morning"
    } else {
      "Afternoon"
    }
  }
}

object HomePageController extends Controller {

  val greeter = RealTimeGreeterService

  def land = Action {
    Ok(views.html.landing(greeter.greeting))
  }
}
