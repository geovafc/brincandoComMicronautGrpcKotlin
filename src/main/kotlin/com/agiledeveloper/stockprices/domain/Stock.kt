package com.agiledeveloper.stockprices.domain


data class Stock (val ticker: String, val price: Int) {

    companion object {
        //buscar preço
        fun fetchPrice(ticker: String) = Stock( ticker, (Math.random() * 2000).toInt() )
    }
}



