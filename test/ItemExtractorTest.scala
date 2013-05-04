import domain.ItemExtractor
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class ItemExtractorTest extends FlatSpec with ShouldMatchers {

  behavior of "Item Extractor"

  private val extractor = new ItemExtractor {}

  it should "extract data from Jira Item XML" in {
    val result = extractor.extractItem(TestXmlData.itemXml)

    result should be(TestXmlData.item)
  }

  it should "allow to make some data optional when extracting" in {
    val result = extractor.extractItem(TestXmlData.itemWithoutOptionalsXml)

    result should be(TestXmlData.itemWithoutOptionals)
  }
}
