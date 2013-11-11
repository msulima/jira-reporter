package support.json

import domain.{DataSetCumulator, Chart, StoryChart}
import play.api.libs.json.Json

trait StoryChartToJsonMapper {
  this: DataSetToJsonMapper =>
  
  def asJson(statistics: StoryChart, title: String) = {
    Json.obj(
      "axisY" -> Map(
        "title" -> title
      ), "data" -> List(
        dataSetAsJson(statistics.created, "Created"),
        dataSetAsJson(statistics.resolved, "Resolved")
      )
    )
  }
}
