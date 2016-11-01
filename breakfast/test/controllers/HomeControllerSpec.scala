package controllers

import org.joda.time.DateTime
import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

class HomeControllerSpec extends PlaySpec with OneAppPerSuite {

  //a fake for illustration
  object FakeMorningGreeter extends  TimeGreetingService {
    def greeting = "Morning"
  }

  //a fake for illustration
  object FakeAfternoonGreeter extends TimeGreetingService {
    def greeting = "Afternoon"
  }

  //can we somehow inject here?
  object TestController extends HomePageController
  val controller = TestController

  "The Home page controller" should {

    "say morning " when {
      val result = controller.land()(FakeRequest(GET, "foo"))
      status(result) mustBe OK
      contentAsString(result) must include ("Lunch?")
      contentAsString(result) must include ("Morning")
    }

    "say afternoon " ignore  {
      val result = controller.land()(FakeRequest(GET, "foo"))
      status(result) mustBe OK
      contentAsString(result) must include("Lunch?")
      contentAsString(result) must include("Afternoon")
    }

    "have some content " when {
      val result = controller.land()(FakeRequest(GET, "foo"))
      status(result) mustBe OK
      contentAsString(result) must include ("Morning want to order Lunch?")
    }

    "Say hello" when {
      "I go to the landing page" in {
        val result = route(app, FakeRequest(GET, "/landing"))
        result.map(contentAsString(_)).get must include ("want to order Lunch?")
      }
    }

    "Not return a 404" when {
      "I go to the route landing" in {
        route(app, FakeRequest(GET, "/landing")).map(status(_)) must not be Some(NOT_FOUND)
      }
    }
  }
}
