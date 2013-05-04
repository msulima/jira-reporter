import domain.{Item, ItemExtractor}
import org.joda.time.Duration
import org.scalatest.FlatSpec

import org.scalatest.matchers.ShouldMatchers


class ItemExtractorTest extends FlatSpec with ShouldMatchers {

  private val expectedTitle = "[PKNCMS-605] Task title"
  private val expectedOriginalEstimate = Duration.standardHours(8)
  private val expectedTimeSpent = Some(Duration.standardSeconds(45000))

  behavior of "Item Extractor"

  private val extractor = new ItemExtractor {}

  it should "extract data from Jira Item XML" in {
    val result = extractor.extract(TestXmlData.xml)

    result should be(Item(expectedTitle, expectedOriginalEstimate, expectedTimeSpent))
  }

  it should "allow to make some data optional when extracting" in {
    val result = extractor.extract(TestXmlData.xmlWithoutOptionals)

    result should be(Item(expectedTitle, expectedOriginalEstimate, None))
  }
}
