package domain

import org.joda.time.Duration

case class Item(title: String, originalEstimate: Duration, spent: Option[Duration])