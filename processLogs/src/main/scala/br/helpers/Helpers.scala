package br.helpers

import java.sql.{ Timestamp}
import br.enumerators.enums
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}

object Helpers {
    def formatDate(dateHourString: String): String = {
   dateHourString.split(":").head
  }









}
