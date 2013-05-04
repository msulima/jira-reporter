package support.xml

import support.jira.Constants.dateFormatter
import org.joda.time.{DateTime, Duration}
import scales.xml._
import ScalesXml._

import Functions._

object Readers {

  def xpathReader[T <: Iterable[XmlPath]](reader: XmlPath => XPath[T]) = (document: XmlPath) =>
    reader(document)

  def stringReader[T <: Iterable[XmlPath]](xpath: XPath[T]): Option[String] =
    for (attributeOpt <- xpath.headOption)
    yield string(attributeOpt)

  def stringAttrReader[T <: Iterable[XmlPath]](attributePath: AttributePaths[T]): Option[String] =
    for (attributeOpt <- attributePath.headOption)
    yield string(attributeOpt)

  def durationReader[T <: Iterable[XmlPath]](xpath: XPath[T]): Option[Duration] =
    for (secondsOpt <- stringAttrReader(xpath \@ "seconds"))
    yield Duration.standardSeconds(secondsOpt.toLong)

  def dateTimeReader[T <: Iterable[XmlPath]](xpath: XPath[T]): Option[DateTime] =
    for (dateTime <- stringReader(xpath))
    yield DateTime.parse(dateTime, dateFormatter)
}
