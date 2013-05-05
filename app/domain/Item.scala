package domain

import org.joda.time.{DateTime, Duration}

case class Item(title: String, originalEstimate: Option[Duration], created: DateTime, resolved: Option[DateTime])
