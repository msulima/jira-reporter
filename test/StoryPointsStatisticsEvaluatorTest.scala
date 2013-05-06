import domain.{StoryPointsStatisticsEvaluator, DataPoint, Item}
import org.joda.time._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import scala.Some

class StoryPointsStatisticsEvaluatorTest extends FlatSpec with ShouldMatchers {

  behavior of "Story Points Statistics Evaluator"

  private val evaluator = new StoryPointsStatisticsEvaluator {}

  private val startDate: DateTime = new DateTime(2010, 1, 1, 0, 0)
  private val endDate: DateTime = startDate.plus(Days.ONE)

  private val item = new Item(null, 
    originalEstimate = Some(Duration.standardDays(1)), created = startDate, resolved = Some(endDate))
  private val otherItem = item.copy(originalEstimate = Some(Duration.standardDays(3)), created = endDate)
  private val notEstimatedItem = item.copy(originalEstimate = Some(Duration.standardDays(0)), created = endDate)
  private val unresolvedItem = item.copy(originalEstimate = None, created = startDate, resolved = None)

  it should "evaluate statistics of items basing on Stories original estimates" in {
    val results = evaluator.evaluate(List(item, otherItem, notEstimatedItem, unresolvedItem))

    results.created should be(Set(DataPoint(startDate, 1), DataPoint(endDate, 3)))
    results.resolved should be(Set(DataPoint(endDate, 4)))
  }
}
