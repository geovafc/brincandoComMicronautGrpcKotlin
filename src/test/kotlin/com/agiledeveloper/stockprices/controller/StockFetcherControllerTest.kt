package com.agiledeveloper.stockprices

import com.agiledeveloper.stockprices.domain.Stock
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.reactivex.Flowable
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import javax.inject.Inject

@Client("/")
interface StockPriceClient {
    @Get("/price/{tickers}")
    fun getPrices(tickers: String): List<Stock>
//    RxJava é uma implementação Java de extensões reativas que nos permite escrever aplicativos
//    assíncronos e orientados a eventos.
    @Get("/pricerx/{tickers}")
    fun getPricesRx(tickers: String): Flowable<Stock>
}


@MicronautTest
class StockFetcherControllerTest(private val embeddedServer: EmbeddedServer) {

    //lateinit significa que vou inicializar essa variável mais tarde e quem cuidará
    //de iniciar essa variável será a injeção de dependências
    @Inject
    lateinit var stockPriceClient: StockPriceClient

    @Test
    fun testServerIsRunning() {
        assert(embeddedServer.isRunning())
    }

    @Test
    fun getPrices() {
        val stocks = stockPriceClient.getPrices("GOOG, AMZN, ORCL")

        assertEquals(3, stocks.size)
        assertEquals("GOOG", stocks[0].ticker)
        assertTrue(stocks[0].price > 0)


    }

    @Test
    fun getPricesRx(){
        val stock = stockPriceClient.getPricesRx("GOOG, AMZN, ORCL")
            .blockingFirst()

        assertEquals("GOOG", stock.ticker)
        assertTrue(stock.price > 0)
    }

}
