package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class BreakfastAppController @Inject() extends Controller {

  def home = Action {
    Ok("OK")
  }

}