package controllers

import play.api.mvc._
import domain._
import scales.xml._
import ScalesXml._
import play.api.Play
import play.api.libs.json.Json
import support.json.{DataSetToJsonMapper, StoryChartToJsonMapper}

object Application extends Controller {

  import play.api.Play.current

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  val storyCountEvaluator = new StoryCountStatisticsEvaluator {}
  val storyPointsEvaluator = new StoryPointsStatisticsEvaluator {}

  val mapper = new StoryChartToJsonMapper with DataSetToJsonMapper {}

  val document = loadResource("sampleDocument.xml")
  val parsed = new DocumentParser with ItemExtractor {}.parse(document)

  val storyCountChart = cumulate(storyCountEvaluator.evaluate(parsed))
  val storyPointsChart = cumulate(storyPointsEvaluator.evaluate(parsed))

  val scaledCountChart = StoryChart(created = DataSetScaler.scaleResults(storyCountChart.created, storyPointsChart.created),
    resolved = storyPointsChart.created)

  def getChart = CQRSAction {
    Ok(Json.obj(
      "storyCount" -> mapper.asJson(storyCountChart, "Story Count"),
      "scaledCountChart" -> mapper.asJson(scaledCountChart, "Scaled Story Count"),
      "storyPoints" -> mapper.asJson(storyPointsChart, "Story Points")
    ))
  }
  
  private def cumulate(chart: StoryChart) =
    chart.copy(created = DataSetCumulator.cumulateResults(chart.created),
      resolved = DataSetCumulator.cumulateResults(chart.resolved))

  private def loadResource(path: String) =
    loadXml(Play.classloader.getResourceAsStream(path))

  private def CQRSAction(body: => Result) = Action {
    body.withHeaders("Access-Control-Allow-Origin" -> "*")
  }
}