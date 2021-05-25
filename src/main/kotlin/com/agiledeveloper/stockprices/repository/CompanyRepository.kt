package com.agiledeveloper.stockprices.repository

import com.agiledeveloper.stockprices.domain.Company
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface CompanyRepository : CrudRepository<Company, Long>{

//@Executable
//fun find(title: String) : Book
}