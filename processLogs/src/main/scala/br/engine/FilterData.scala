package br

import br.domain.httpRequest
import br.enumerators.enums
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.functions.{count, countDistinct, desc, sum}

object FilterData {

  def uniqueHosts(httpRequestLoaded: Dataset[httpRequest] ) = {

    httpRequestLoaded.select(countDistinct("host"))
                      .withColumnRenamed("count(DISTINCT host)", "UNIQUE HOSTS")
  }

  def totalErrors(httpRequestLoaded: Dataset[httpRequest] ) = {
    httpRequestLoaded.where("returnCode == 404")
                     .select(count("returnCode"))
                      .withColumnRenamed("count(returnCode)", "TOTAL OF 404 ERRORS")
  }

  def topRequestErrors(httpRequestLoaded: Dataset[httpRequest]) = {

    httpRequestLoaded.where("returnCode == 404")
                     .groupBy("request", "returnCode")
                     .count().orderBy(desc("count"))
                     .limit(enums.topFive)
  }

  def errorsPerDay(httpRequestLoaded: Dataset[httpRequest]) = {
    httpRequestLoaded.where("returnCode == 404")
                     .groupBy("requestDate", "returnCode")
                     .count().orderBy(desc("count"))
                     .withColumnRenamed("count", "Number of 404 errors per day")


  }

  def bytesReturned(httpRequestLoaded: Dataset[httpRequest]) = {
    httpRequestLoaded.select(sum("bytesSize"))
                      .withColumnRenamed("sum(bytesSize)", "SUM OF RETURN BYTES")
  }



}
