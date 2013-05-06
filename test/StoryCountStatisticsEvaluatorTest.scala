import domain.{StoryCountStatisticsEvaluator, DataPoint, Item}
import org.joda.time._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import scala.Some

class StoryCountStatisticsEvaluatorTest extends FlatSpec with ShouldMatchers {

  behavior of "Story Count Statistics Evaluator"

  private val evaluator = new StoryCountStatisticsEvaluator {}

  private val startDate: DateTime = new DateTime(2010, 1, 1, 0, 0)
  private val endDate: DateTime = startDate.plus(Days.ONE)
  private val item = new Item(null, 
    originalEstimate = Some(Duration.standardDays(1)), created = startDate, resolved = Some(endDate))
  private val otherItem = item.copy(created = endDate)
  private val unresolvedItem = item.copy(created = startDate, resolved = None)
  private val nonEstimatedItem = item.copy(originalEstimate = None)
  private val itemWithLowEstimation = item.copy(originalEstimate = Some(Duration.standardHours(7)))

  it should "evaluate statistics of items basing on count of Stories" in {
    val results = evaluator.evaluate(List(item, otherItem, unresolvedItem))

    results.created should be(Set(DataPoint(startDate, 2), DataPoint(endDate, 1)))
    results.resolved should be(Set(DataPoint(endDate, 2)))
  }

  it should "leave out Stories without originalEstimate" in {
    val results = evaluator.evaluate(List(nonEstimatedItem, itemWithLowEstimation))

    results.created should be(Set())
    results.resolved should be(Set())
  }
}
