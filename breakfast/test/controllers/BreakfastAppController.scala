package controllers

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

class BreakfastAppControllerSpec extends PlaySpec with OneAppPerSuite {

  "BreakfastAppController " should {
    "not return 404 " when {
      "we try to hit the root /home" in {
        route(app, FakeRequest(GET, "/home")).map(status(_)) must not be  Some(NOT_FOUND)
      }
    }
    "render a page" when {
      "we try to hit the route /home" in {
        val result = route(app, FakeRequest(GET, "/home"))
        result.map(status(_)) mustBe Some(OK)
        result.map(contentAsString(_)).get must include ("Welcome to Play")
      }
    }
  }
}
