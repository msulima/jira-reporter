package controllers

import domain.{DataSetOperations, Chart, StoryChart}
import play.api.libs.json.Json

object StoryChartToJsonMapper {

  def asJson(statistics: StoryChart, title: String) = {
    Json.obj(
      "axisY" -> Map(
        "title" -> title
      ), "data" -> List(
        dataSet(statistics.created, "Created"),
        dataSet(statistics.resolved, "Resolved")
      )
    )
  }

  private def dataSet(data: Chart.DataSet, legend: String) =
    Map(
      "type" -> Json.toJson("line"),
      "showInLegend" -> Json.toJson(true),
      "legendText" -> Json.toJson(legend),
      "dataPoints" -> Json.toJson(dataPoints(data))
    )

  private def dataPoints(data: Chart.DataSet) =
    for (point <- DataSetOperations.cumulateResults(data).toList)
    yield Map("x" -> Json.toJson(point.x.getMillis), "y" -> Json.toJson(point.y))
}
