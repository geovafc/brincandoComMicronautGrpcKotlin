package com.agiledeveloper.stockprices

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.agiledeveloper.stockprices")
		.start()
}

