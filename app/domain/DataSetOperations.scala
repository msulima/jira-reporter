package domain

object DataSetOperations {

  def cumulateResults(results: Chart.DataSet): Chart.DataSet = {
    var sum = 0
    for (i <- results)
    yield {
      sum += i
      sum
    }
  }
}
