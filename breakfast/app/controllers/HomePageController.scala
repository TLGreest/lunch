package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class HomePageController @Inject() extends Controller {

  def land(message :String = "good morning") = Action {
    Ok(views.html.landing(message))
  }

}

