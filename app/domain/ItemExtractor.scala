package domain

import support.xml.Readers._

import scales.xml._
import ScalesXml._

trait ItemExtractor {

  val title = xpathReader(_ \* "title") andThen stringReader
  val originalEstimate = xpathReader(_ \* "timeoriginalestimate") andThen durationReader
  val spent = xpathReader(_ \* "timespent") andThen durationReader
  val created = xpathReader(_ \* "created") andThen dateTimeReader
  val resolved = xpathReader(_ \* "resolved") andThen dateTimeReader

  def extractItem(document: XmlPath): Item = {
    Item(title = title(document).get, originalEstimate = originalEstimate(document).get, spent = spent(document),
      created = created(document).get, resolved = resolved(document))
  }
}