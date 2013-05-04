package support.time

import org.joda.time.{Interval, DateTime}

object DaysRange {

  def fromInterval(interval: Interval) =
    (DaysStream(interval.getStart) takeWhile isBeforeOrEqual(interval.getEnd)).toIterable

  private def isBeforeOrEqual(end: DateTime): (DateTime) => Boolean = {
    !_.isAfter(end)
  }
}
