import domain.{Item, StatisticsEvaluator}
import org.joda.time._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import scala.Some

class StatisticsEvaluatorTest extends FlatSpec with ShouldMatchers {

  behavior of "Statistics Evaluator"

  private val evaluator = new StatisticsEvaluator {}

  private val startDate: DateTime = new DateTime(2010, 1, 1, 0, 0)
  private val endDate: DateTime = startDate.plus(Days.ONE)
  private val item = new Item(null, null, null, created = startDate, resolved = Some(endDate))
  private val unresolvedItem = new Item(null, null, null, created = startDate, resolved = None)
  private val expectedInterval = new Interval(startDate, endDate)

  it should "evaluate statistics of items" in {
    val results = evaluator.evaluate(List(item, unresolvedItem))

    results.interval should be(expectedInterval)
    results.created should be(List(2, 0))
    results.resolved should be(List(0, 1))
  }
}
