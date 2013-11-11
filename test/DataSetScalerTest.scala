import domain.{DataSetScaler, DataPoint}
import org.joda.time.{Days, DateTime}
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import scala.collection.SortedSet

class DataSetScalerTest extends FlatSpec with ShouldMatchers {

  behavior of "Data Set Scaler"

  private val date: DateTime = new DateTime(2010, 1, 1, 0, 0)

  private val points = SortedSet(DataPoint(date, 10), DataPoint(date.plus(Days.ONE), 20), DataPoint(date.plus(Days.TWO), 30))
  private val reference = SortedSet(DataPoint(date, 0), DataPoint(date.plus(Days.ONE), 45))
  private val expectedPoints = SortedSet(DataPoint(date, 15), DataPoint(date.plus(Days.ONE), 30), DataPoint(date.plus(Days.TWO), 45))

  it should "scale two Data Sets to common factor" in {
    val result = DataSetScaler.scaleResults(points, reference)

    result.toSeq should be(expectedPoints.toSeq)
  }
}
