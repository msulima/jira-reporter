package domain

import scala.collection.SortedSet

object DataSetScaler {

  def scaleResults(results: SortedSet[DataPoint], reference: SortedSet[DataPoint]) = { 
    val factor = reference.toSeq.last.y / (results.toSeq.last.y.toDouble)
    for(item <- results)
      yield item.copy(y = Math.ceil(item.y * factor).toInt)
  }
}
