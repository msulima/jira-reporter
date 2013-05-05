package domain

import org.joda.time.DateTime
import scala.collection.SortedSet

trait StatisticsEvaluator {

  def evaluate(items: Iterable[Item]): StoryChart = {
    val closedByDate = sorted(createHistogram(items.map(_.created)))
    val resolvedByDate = sorted(createHistogram(items.filter(_.resolved.isDefined).map(_.resolved.get)))

    StoryChart(created = closedByDate, resolvedByDate)
  }

  private def createHistogram(dates: Iterable[DateTime]) =
    for ((date, items) <- dates.groupBy(_.withTimeAtStartOfDay()))
    yield DataPoint(date, items.size)

  private def sorted(histogram: Iterable[DataPoint]): SortedSet[DataPoint] =
    SortedSet[DataPoint]() ++ histogram
}
