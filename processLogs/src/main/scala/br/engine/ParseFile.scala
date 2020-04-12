package br

import br.domain.httpRequest
import br.enumerators.enums
import br.helpers.Helpers

object ParseFile {

  def parseLine(line : String) : httpRequest = {
    // Realiza o parse de cada linha do arquivo para o tipo da case class httpRequest
    val host = this.parseHost(line)
    val requestTimestamp = this.parseDateRequest(line)
    val (request,positionEndRequest) = this.parseRequest(line)
    val (returnCode,bytesSize) = this.parseCodeAndSize(line,positionEndRequest)

    //retorna os dados na case class
    httpRequest(host, requestTimestamp, request, returnCode, bytesSize)

  }

  def parseHost(line : String) : String = {

    //encontra o host na string. Host é a primeira parte da linha até o primeiro espaço
    try {
      line.split(enums.spaceString).toList.head.trim
    } catch {
      case e: Exception => enums.emputyChar
    }

  }

  def parseDateRequest(line : String): String ={
    //[02/Aug/1995:00:00:01 -0400]
    //A data da request é iniciada no "[" até o "]", após identificar toda a string trunco a data para armazenar apenas DD/MON/YYYY
    try {
      Helpers.formatDate(
        line.substring(
          line.indexOf(enums.squareBracketOpen, enums.positionOne) + 1,
          line.indexOf(enums.squareBracketClose, enums.positionOne)))
    } catch {
      case e: Exception => enums.emputyChar
    }
  }


  def parseRequest(line : String): (String,Int) ={
    //"GET / HTTP/1.0"
    // A url é iniciada por uma " e finalizada por ".
    //Encontro a posição da primeira " e da segunda " na string
    val positionStrarRequest = line.indexOf(enums.doubleQuoteChar, enums.positionOne) + 1
    val positionEndRequest = line.indexOf(enums.doubleQuoteChar, positionStrarRequest)

    //retorno a url encontrada na linha
    val request =
      try {
        line.substring(positionStrarRequest, positionEndRequest)
      } catch {
        case e: Exception => enums.emputyChar
      }
    (request,positionEndRequest)
  }

  def parseCodeAndSize(line : String, positionStart : Int): (Int,Int) ={

    //O código de retorno e byteSize estão posicionados na linha após a url requisitada
    // Inicio a extração no caracter logo após o fechamento da url(")

    //Retiro apenas número da string, removendo os espaços e caracter ] do final da linha
    val codeAndSize = line.substring(positionStart + 1).split("\\D+").filter(_.nonEmpty).toList.map(_.toInt)

    //Código de retono está na primeira posição da lista
    val returnCode = try {
      codeAndSize(0)
    } catch {
      case e: Exception => enums.zero
    }

    //Quantidade de bytes retornado está na segunda posição da lista
    val bytesSize = try {
      codeAndSize(1)
    } catch {
      case e: Exception => enums.zero
    }

    (returnCode,bytesSize)

  }





}


