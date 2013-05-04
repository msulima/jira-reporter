import java.io.{File, ByteArrayInputStream}
import java.net.URI
import scales.xml._
import ScalesXml._

// to add friendly and consistent access to the data model

object TestXmlData {

  val xml = loadXml(getClass.getResourceAsStream("xml/item.xml"))
  val xmlWithoutOptionals = loadXml(getClass.getResourceAsStream("xml/itemWithoutOptionalFields.xml"))
}
