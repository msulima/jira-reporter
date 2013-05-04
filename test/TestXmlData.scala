import domain.Item
import org.joda.time.Duration
import scales.utils._
import scales.xml._
import ScalesXml._

object TestXmlData {

  private val expectedItemTitle = "[FOOBAR-123] Task title"
  private val expectedItemWithoutOptionalsTitle = "[FOOBAR-456] Task without time spent"
  private val expectedOriginalEstimate = Duration.standardHours(8)
  private val expectedTimeSpent = Some(Duration.standardSeconds(45000))

  val document = loadResource("xml/document.xml")
  val itemXml = getItem(document, 0)
  val itemWithoutOptionalsXml = getItem(document, 1)

  val item = Item(expectedItemTitle, expectedOriginalEstimate, expectedTimeSpent)
  val itemWithoutOptionals = Item(expectedItemWithoutOptionalsTitle, expectedOriginalEstimate, None)
  val itemsInDocument = List(item, itemWithoutOptionals)

  private def loadResource(path: String) =
    loadXml(getClass.getResourceAsStream(path))

  private def getItem(document: Doc, index: Int) =
    (top(document) \\* "item").toList(index)
}
