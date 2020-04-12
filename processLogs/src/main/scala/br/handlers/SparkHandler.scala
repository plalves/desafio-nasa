package br.handlers
import org.apache.spark.sql.{SQLContext, SparkSession}


object SparkHandler {

  def getSparkSession: SparkSession = {sparkSession }

  val sparkSession: SparkSession = SparkSession.builder().appName("httpRequest").getOrCreate()



}
