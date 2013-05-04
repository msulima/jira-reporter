import domain.{ItemExtractor, DocumentParser}
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class DocumentParserTest extends FlatSpec with ShouldMatchers {

  private val parser = new DocumentParser with ItemExtractor {}

  behavior of "Document Parser"

  it should "parse whole document and extract items" in {
    val result = parser.parse(TestXmlData.document)

    result should be(TestXmlData.itemsInDocument)
  }
}
