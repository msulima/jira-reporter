import domain.DataSetOperations
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class DataSetOperationsTest extends FlatSpec with ShouldMatchers {

  behavior of "Data Set Operations"

  it should "cumulate results in DataSet" in {
    val result = DataSetOperations.cumulateResults(List(1, 1, 1, 7))

    result should be(List(1, 2, 3, 10))
  }
}
