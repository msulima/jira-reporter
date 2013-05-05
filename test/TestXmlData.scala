import domain.Item
import support.jira.Constants.dateFormatter
import org.joda.time.{DateTime, Duration}
import scales.utils._
import scales.xml._
import ScalesXml._

object TestXmlData {

  private val expectedItemTitle = "[FOOBAR-123] Task title"
  private val expectedItemWithoutOptionalsTitle = "[FOOBAR-456] Unresolved task without time spent"
  private val expectedOriginalEstimate = Some(Duration.standardHours(8))
  private val expectedCreated = DateTime.parse("Sat, 3 Sep 2011 10:36:34 +0200", dateFormatter)
  private val expectedResolved = Some(DateTime.parse("Tue, 6 Sep 2011 15:58:37 +0200", dateFormatter))

  val document = loadResource("xml/document.xml")
  val itemXml = getItem(document, 0)
  val itemWithoutOptionalsXml = getItem(document, 1)

  val item = Item(expectedItemTitle, expectedOriginalEstimate, expectedCreated, expectedResolved)
  val itemWithoutOptionals = Item(expectedItemWithoutOptionalsTitle, None, expectedCreated, None)
  val itemsInDocument = List(item, itemWithoutOptionals)

  private def loadResource(path: String) =
    loadXml(getClass.getResourceAsStream(path))

  private def getItem(document: Doc, index: Int) =
    (top(document) \\* "item").toList(index)
}
