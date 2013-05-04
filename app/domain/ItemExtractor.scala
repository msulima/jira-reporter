package domain

import Readers._

import scales.xml._
import ScalesXml._

trait ItemExtractor {

  val title = xpathReader(_ \* "title") andThen stringReader
  val originalEstimate = xpathReader(_ \* "timeoriginalestimate") andThen durationReader
  val spent = xpathReader(_ \* "timespent") andThen durationReader

  def extractItem(document: XmlPath): Item = {
    Item(title = title(document), originalEstimate = originalEstimate(document).get, spent = spent(document))
  }
}