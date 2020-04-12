package br.domain

case class httpRequest(host: String,
                       requestDate : String,
                       request: String,
                       returnCode : Int,
                       bytesSize : Int
                      )
