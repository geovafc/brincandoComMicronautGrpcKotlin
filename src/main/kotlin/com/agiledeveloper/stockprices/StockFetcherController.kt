package com.agiledeveloper.stockprices

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Flowable
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit

@Controller("/")
class StockFetcherController {

    //    map () aplica uma função a todos os elementos em uma coleção
    @Get("/price/{tickers}")
    fun getPrices(tickers: String): List<Stock> {
        println("processing... tickers $tickers.")

        return tickers.split(",")
            .map(Stock::fetchPrice)
    }

    @Get("/pricerx/{tickers}")
    fun getPricesRx(tickers: String): Flowable<Stock> {
        println("processing... tickers $tickers.")

        return Flowable.fromIterable( tickers.split(",") )
            .delay(2, TimeUnit.SECONDS)
            .map(Stock::fetchPrice)
    }

//    @Get("/price/{tickers}")
    fun getPricesWithFailed(tickers: String): List<Stock> {
        println("processing... tickers $tickers.")

        if (Math.random() > 0.5)
            throw RuntimeException("Something went wrong ${LocalDateTime.now()}")
        else
            println("Sucess ${LocalDateTime.now()}")

        return tickers.split(",")
            .map(Stock::fetchPrice)
    }


}