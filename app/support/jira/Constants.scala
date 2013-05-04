package support.jira

import org.joda.time.format.DateTimeFormat
import java.util.Locale

object Constants {

  val dateFormatter = DateTimeFormat.forPattern("E, d MMM y H:m:s Z").withLocale(Locale.ENGLISH)
}
