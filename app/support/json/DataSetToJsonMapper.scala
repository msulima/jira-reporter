package support.json

import domain.{DataSetCumulator, Chart}
import play.api.libs.json.Json

trait DataSetToJsonMapper {

  def dataSetAsJson(data: Chart.DataSet, legend: String) =
    Map(
      "type" -> Json.toJson("line"),
      "showInLegend" -> Json.toJson(true),
      "legendText" -> Json.toJson(legend),
      "dataPoints" -> Json.toJson(dataPoints(data))
    )

  private def dataPoints(data: Chart.DataSet) =
    for (point <- data.toList)
    yield Map("x" -> Json.toJson(point.x.getMillis), "y" -> Json.toJson(point.y))
}
