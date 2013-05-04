package domain

import scales.xml
import Readers._

import scales.utils._
import scales.xml._
import ScalesXml._

trait ItemExtractor {

  val title = xpathReader(_ \* "title") andThen stringReader
  val originalEstimate = xpathReader(_ \* "timeoriginalestimate") andThen durationReader
  val spent = xpathReader(_ \* "timespent") andThen durationReader

  def extract(document: Doc): Item = {
    val path: Path[XmlItem, Elem, xml.XmlTypes#XCC] = top(document)

    Item(title = title(path), originalEstimate = originalEstimate(path).get, spent = spent(path))
  }
}