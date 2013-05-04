package controllers

import play.api.mvc._
import domain.{ItemExtractor, DocumentParser, StatisticsEvaluator}
import scales.xml._
import ScalesXml._
import play.api.Play

object Application extends Controller {

  import play.api.Play.current

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  val evaluator = new StatisticsEvaluator {}

  def getChart = CQRSAction {
    val document = loadResource("sampleDocument.xml")
    val parsed = new DocumentParser with ItemExtractor {}.parse(document)
    Ok(StoryChartToJsonMapper.asJson(evaluator.evaluate(parsed)))
  }

  private def loadResource(path: String) =
    loadXml(Play.classloader.getResourceAsStream(path))

  private def CQRSAction(body: => Result) = Action {
    body.withHeaders("Access-Control-Allow-Origin" -> "*")
  }
}