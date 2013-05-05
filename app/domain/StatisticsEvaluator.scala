package domain

import org.joda.time.DateTime
import scala.collection.SortedSet

trait StatisticsEvaluator {

  def evaluate(items: Iterable[Item]): StoryChart = {
    val closedByDate = processItems(groupByCreated)(items)
    val resolvedByDate = processItems(groupByResolved)(items)

    StoryChart(created = closedByDate, resolvedByDate)
  }

  private def processItems(groupByDate: (Iterable[Item]) => Map[DateTime, Iterable[Item]]) =
    filterEstimated andThen groupByDate andThen createHistogram andThen sort

  private def filterEstimated = (items: Iterable[Item]) =>
    items.filter(_.originalEstimate.isDefined)

  private def groupByCreated(items: Iterable[Item]) =
    groupBy(_.created)(items)

  private def groupByResolved(items: Iterable[Item]) =
    groupBy(_.resolved.get)(items.filter(_.resolved.isDefined))

  private def groupBy(dateGetter: (Item) => DateTime)(items: Iterable[Item]) =
    items.groupBy(item => dateGetter(item).withTimeAtStartOfDay())

  private def createHistogram(dates: Map[DateTime, Iterable[Item]]) =
    for ((date, items) <- dates)
    yield DataPoint(date, evaluateX(items))

  protected def evaluateX(items: Iterable[Item]): Int

  private def sort(histogram: Iterable[DataPoint]): SortedSet[DataPoint] =
    SortedSet[DataPoint]() ++ histogram
}

trait StoryCountStatisticsEvaluator extends StatisticsEvaluator {

  override def evaluateX(items: Iterable[Item]) =
    items.size
}

trait StoryPointsStatisticsEvaluator extends StatisticsEvaluator {

  override def evaluateX(items: Iterable[Item]): Int =
    items.foldLeft(0)((a, b) => a + b.originalEstimate.map(_.getStandardHours.toInt).getOrElse(0))
}
