package domain

import org.joda.time.DateTime
import Chart.DataSet
import support.time.DateTimeOrdering
import scala.collection.SortedSet

object Chart {
  type DataSet = SortedSet[DataPoint]
}

case class StoryChart(created: DataSet, resolved: DataSet)

case class DataPoint(x: DateTime, y: Int)

object DataPoint {

  implicit val ordering: Ordering[DataPoint] = Ordering.by((d: DataPoint) => d.x)(DateTimeOrdering)
}