package support.time

import org.joda.time.{Days, DateTime}

object DaysStream {

  def apply(startDay: DateTime): Stream[DateTime] = {
    def loop(d: DateTime): Stream[DateTime] = d #:: loop(d.plus(Days.ONE))
    loop(startDay.withTimeAtStartOfDay())
  }
}
