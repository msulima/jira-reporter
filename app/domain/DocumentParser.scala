package domain

import support.xml.Readers._

import scales.utils._
import scales.xml._
import ScalesXml._

trait DocumentParser {
  this: DocumentParser with ItemExtractor =>

  val items = xpathReader(_ \* "channel" \* "item")

  def parse(document: Doc): Iterable[Item] = {
    val path = top(document)

    items(path).map(extractItem)
  }
}
