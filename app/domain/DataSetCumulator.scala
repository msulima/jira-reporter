package domain

object DataSetCumulator {

  def cumulateResults(results: Chart.DataSet): Chart.DataSet = {
    var sum = 0
    for (i <- results)
    yield {
      sum += i.y
      DataPoint(i.x, sum)
    }
  }
}
