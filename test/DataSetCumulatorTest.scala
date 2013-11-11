import domain.{DataPoint, DataSetCumulator}
import org.joda.time.{Days, DateTime}
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import scala.collection.SortedSet

class DataSetCumulatorTest extends FlatSpec with ShouldMatchers {

  behavior of "Data Set Cumulator"

  private val date: DateTime = new DateTime(2010, 1, 1, 0, 0)

  private val points = SortedSet(DataPoint(date, 1), DataPoint(date.plus(Days.ONE), 1), DataPoint(date.plus(Days.TWO), 8))
  private val expectedPoints = SortedSet(DataPoint(date, 1), DataPoint(date.plus(Days.ONE), 2), DataPoint(date.plus(Days.TWO), 10))

  it should "cumulate results in DataSet" in {
    val result = DataSetCumulator.cumulateResults(points)

    result.toSeq should be(expectedPoints.toSeq)
  }
}
