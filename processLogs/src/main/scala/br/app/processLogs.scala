package br.app

import br.domain.httpRequest
import br.handlers.SparkHandler
import br.{FilterData, ParseFile}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object processLogs {

  def main(args: Array[String]) = {

    val ss: SparkSession = SparkHandler.getSparkSession
    import ss.implicits._

    println("<<<<< INICIO DO PROCESSAMENTO >>>>>")

    val dataPath = args.head

    println("dataPath >>> " + dataPath)

    //le os arquivos encontrados no diretorio informado
    val dataFiles: DataFrame = ss.read.text(dataPath)

    //Transforma os arquivos em um DataSet
    val httpRequestLoaded: Dataset[httpRequest] = dataFiles.map(m => ParseFile.parseLine(m.toString()) ).cache()

    //Número de hosts únicos.
    FilterData.uniqueHosts(httpRequestLoaded).show()

    //total de erros 404
    FilterData.totalErrors(httpRequestLoaded).show()

    //Os 5 URLs mais causaram erro 404
    FilterData.topRequestErrors(httpRequestLoaded).show()

    //Quantidade de erros 404 por dia
    FilterData.errorsPerDay(httpRequestLoaded).show()

    //O total de bytes retornados
    FilterData.bytesReturned(httpRequestLoaded).show()

    println(" FIM DO PROCESSAMENTO")


  }
}
