import java.io.ByteArrayInputStream
import scales.xml._
import ScalesXml._

// to add friendly and consistent access to the data model

object TestXmlData {

  val xmlText: String = """<item>
                          |    <title>[FOOBAR-605] Task title</title>
                          |    <link>https://foo.jira.com/browse/FOOBAR-605</link>
                          |    <project id="10022" key="FOOBAR">Foo bar project</project>
                          |    <key id="12282">FOOBAR-605</key>
                          |    <summary>Setup CMS in standalone mode for Jetty and for Glassfish</summary>
                          |    <type id="3">Task</type>
                          |    <priority id="1">Blocker</priority>
                          |    <status id="6">Closed</status>
                          |    <resolution id="1">Fixed</resolution>
                          |    <labels></labels>
                          |    <created>Sat, 3 Sep 2011 10:36:34 +0200</created>
                          |    <updated>Wed, 7 Sep 2011 09:31:17 +0200</updated>
                          |    <resolved>Tue, 6 Sep 2011 15:58:37 +0200</resolved>
                          |    <fixVersion>Iteration 21</fixVersion>
                          |    <component>Development</component>
                          |    <timeoriginalestimate seconds="28800">1 day</timeoriginalestimate>
                          |    <timeestimate seconds="0">0 minutes</timeestimate>
                          |    <timespent seconds="45000">1 day, 4 hours, 30 minutes</timespent>
                          |</item>"""
  val xmlTextWithoutOptionals = """<item>
                                  |    <title>[FOOBAR-605] Task title</title>
                                  |    <link>https://foo.jira.com/browse/FOOBAR-605</link>
                                  |    <project id="10022" key="FOOBAR">Foo bar project</project>
                                  |    <key id="12282">FOOBAR-605</key>
                                  |    <summary>Setup CMS in standalone mode for Jetty and for Glassfish</summary>
                                  |    <type id="3">Task</type>
                                  |    <priority id="1">Blocker</priority>
                                  |    <status id="6">Closed</status>
                                  |    <resolution id="1">Fixed</resolution>
                                  |    <labels></labels>
                                  |    <created>Sat, 3 Sep 2011 10:36:34 +0200</created>
                                  |    <updated>Wed, 7 Sep 2011 09:31:17 +0200</updated>
                                  |    <resolved>Tue, 6 Sep 2011 15:58:37 +0200</resolved>
                                  |    <fixVersion>Iteration 21</fixVersion>
                                  |    <component>Development</component>
                                  |    <timeoriginalestimate seconds="28800">1 day</timeoriginalestimate>
                                  |    <timeestimate seconds="0">0 minutes</timeestimate>
                                  |</item>"""

  val xml = loadXml(new ByteArrayInputStream(xmlText.getBytes("UTF-8")))
  val xmlWithoutOptionals = loadXml(new ByteArrayInputStream(xmlTextWithoutOptionals.getBytes("UTF-8")))
}
