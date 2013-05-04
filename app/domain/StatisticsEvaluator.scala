package domain

import support.time.DateTimeOrdering._
import org.joda.time.{Interval, DateTime}
import support.time.DaysStream

trait StatisticsEvaluator {

  def evaluate(items: Iterable[Item]): StoryChart = {
    val closedByDate = createHistogram(items.map(_.created))
    val resolvedByDate = createHistogram(items.filter(_.resolved.isDefined).map(_.resolved.get))

    val interval = getMaximumInterval(closedByDate, resolvedByDate)

    StoryChart(interval, expandDates(interval, closedByDate), expandDates(interval, resolvedByDate))
  }

  private def createHistogram(dates: Iterable[DateTime]) =
    dates.groupBy(_.withTimeAtStartOfDay()).mapValues(_.size).withDefaultValue(0)

  private def getMaximumInterval(closedByDate: Map[DateTime, Int], resolvedByDate: Map[DateTime, Int]) = {
    val allDates = closedByDate.keySet ++ resolvedByDate.keySet
    new Interval(allDates.min, allDates.max)
  }

  private def expandDates(interval: Interval, histogram: Map[DateTime, Int]) =
    for (day <- DaysStream(interval.getStart) takeWhile isBeforeOrEqual(interval.getEnd))
    yield histogram(day)

  def isBeforeOrEqual(end: DateTime): (DateTime) => Boolean = {
    !_.isAfter(end)
  }
}
