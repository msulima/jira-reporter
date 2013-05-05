package domain

import org.joda.time.DateTime
import scala.collection.SortedSet

trait StatisticsEvaluator {

  def evaluate(items: Iterable[Item]): StoryChart = {
    val closedByDate = (groupByCreated _ andThen createHistogram andThen sort)(items)
    val resolvedByDate = (groupByResolved _ andThen createHistogram andThen sort)(items)

    StoryChart(created = closedByDate, resolvedByDate)
  }

  private def groupByCreated(items: Iterable[Item]) =
    groupByDate(_.created)(items)

  private def groupByResolved(items: Iterable[Item]) =
    groupByDate(_.resolved.get)(items.filter(_.resolved.isDefined))

  private def groupByDate(dateGetter: (Item) => DateTime)(items: Iterable[Item]) =
    items.groupBy(item => dateGetter(item).withTimeAtStartOfDay())

  protected def createHistogram(dates: Map[DateTime, Iterable[Item]]): Iterable[DataPoint]

  private def sort(histogram: Iterable[DataPoint]): SortedSet[DataPoint] =
    SortedSet[DataPoint]() ++ histogram
}

trait StoryCountStatisticsEvaluator extends StatisticsEvaluator {

  override def createHistogram(dates: Map[DateTime, Iterable[Item]]) =
    for ((date, items) <- dates)
    yield DataPoint(date, items.size)
}

trait StoryPointsStatisticsEvaluator extends StatisticsEvaluator {

  override def createHistogram(dates: Map[DateTime, Iterable[Item]]) =
    for ((date, items) <- dates)
    yield DataPoint(date, items.foldLeft(0)((a, b) => a + b.originalEstimate.getStandardHours.toInt))
}
