package controllers

import play.api.mvc._
import domain._
import scales.xml._
import ScalesXml._
import play.api.Play
import play.api.libs.json.Json

object Application extends Controller {

  import play.api.Play.current

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  val storyCountEvaluator = new StoryCountStatisticsEvaluator {}
  val storyPointsEvaluator = new StoryPointsStatisticsEvaluator {}

  def getChart = CQRSAction {
    val document = loadResource("sampleDocument.xml")
    val parsed = new DocumentParser with ItemExtractor {}.parse(document)
    Ok(Json.obj(
      "storyCount" -> StoryChartToJsonMapper.asJson(storyCountEvaluator.evaluate(parsed), "Story Count"),
      "storyPoints" -> StoryChartToJsonMapper.asJson(storyPointsEvaluator.evaluate(parsed), "Story Points")
    ))
  }

  private def loadResource(path: String) =
    loadXml(Play.classloader.getResourceAsStream(path))

  private def CQRSAction(body: => Result) = Action {
    body.withHeaders("Access-Control-Allow-Origin" -> "*")
  }
}