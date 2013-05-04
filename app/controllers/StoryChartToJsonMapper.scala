package controllers

import domain.{DataSetOperations, Chart, StoryChart}
import play.api.libs.json.{JsValue, Json}
import support.time.DaysRange

object StoryChartToJsonMapper {

  private val createdStyle = Map(
    "fillColor" -> "rgba(220,220,220,0.5)",
    "strokeColor" -> "rgba(220,220,220,1)",
    "pointColor" -> "rgba(220,220,220,1)",
    "pointStrokeColor" -> "#fff"
  ).mapValues(Json.toJson(_))

  private val resolvedStyle = Map(
    "fillColor" -> "#E01B6A",
    "strokeColor" -> "#C20A54",
    "pointColor" -> "#C20A54",
    "pointStrokeColor" -> "#E04F89"
  ).mapValues(Json.toJson(_))

  def asJson(statistics: StoryChart) = {
    Json.obj(
      "labels" -> labels(statistics),
      "datasets" -> List(
        dataSet(statistics.created, createdStyle),
        dataSet(statistics.resolved, resolvedStyle)
      )
    )
  }

  private def labels(statistics: StoryChart) =
    for (day <- DaysRange.fromInterval(statistics.interval))
    yield day.toString("yyyy-MM-dd")

  private def dataSet(data: Chart.DataSet, style: Map[String, JsValue]) =
    style + ("data" -> Json.toJson(DataSetOperations.cumulateResults(data)))
}
