package support.xml

import org.joda.time.Duration
import scales.xml._
import ScalesXml._

import Functions._

object Readers {

  def xpathReader[T <: Iterable[XmlPath]](reader: XmlPath => XPath[T]) = (document: XmlPath) =>
    reader(document)

  def stringReader[T <: Iterable[XmlPath]](xpath: XPath[T]): String =
    string(xpath)(xpathToTextValue)

  def stringAttrReader[T <: Iterable[XmlPath]](attributePath: AttributePaths[T]): Option[String] =
    for (attributeOpt <- attributePath.headOption)
    yield string(attributeOpt)

  def durationReader[T <: Iterable[XmlPath]](xpath: XPath[T]): Option[Duration] =
    for (secondsOpt <- stringAttrReader(xpath \@ "seconds"))
    yield Duration.standardSeconds(secondsOpt.toLong)
}
