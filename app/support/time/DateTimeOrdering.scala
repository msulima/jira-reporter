package support.time

import org.joda.time.DateTime

class DateTimeOrdering

object DateTimeOrdering extends Ordering[DateTime] {
  implicit val ordering = this

  def compare(x: DateTime, y: DateTime) = x.compareTo(y)
}