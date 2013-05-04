package domain

import org.joda.time.Interval
import Chart.DataSet

object Chart {
  type DataSet = Iterable[Int]
}

case class StoryChart(interval: Interval, created: DataSet, resolved: DataSet)
