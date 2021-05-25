package com.agiledeveloper.stockprices.controller.grpc

import com.agiledeveloper.stockprices.service.GreetingService
import helloworld.GreeterGrpcKt
import helloworld.HelloReply
import helloworld.HelloRequest
import javax.inject.Singleton

@Singleton
@Suppress("unused")
//Uma co - rotina é uma instância de computação que pode ser suspensa . É conceitualmente
// semelhante a um thread, no sentido de que é necessário um bloco de código para ser
// executado que funciona simultaneamente com o restante do código.
// No entanto, uma co-rotina não está ligada a nenhum thread em particular.
// Ele pode suspender sua execução em um thread e retomar em outro.

//greetingService está sendo injetava via construtor
class GreetingEndpoint(val greetingService: GreetingService) : GreeterGrpcKt.GreeterCoroutineImplBase() {

//suspend fun é simplesmente uma função que pode ser pausada e retomada posteriormente.
// Eles podem executar uma operação de longa duração e esperar que ela seja concluída sem
// bloquear.
    override suspend fun sayHello(request: HelloRequest): HelloReply {

    val message = greetingService.sayHello(request.name)
    //criar um dto com extends functions para fazer esse parse
    val reply = HelloReply
        .newBuilder()
        .setMessage(message)
        .build()

        return reply
    }

}