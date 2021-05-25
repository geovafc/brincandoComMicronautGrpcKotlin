package com.agiledeveloper.stockprices.controller.rest

import com.agiledeveloper.stockprices.repository.CompanyRepository
import com.agiledeveloper.stockprices.domain.Company
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import org.slf4j.LoggerFactory
import javax.inject.Inject

@Controller("/company")
class CompanyController {
    @Inject
    lateinit var companyRepository: CompanyRepository

    val log = LoggerFactory.getLogger(Company::class.java)


    @Post
    fun saveCompany(@Body companyRequest: Company): HttpResponse<Any> {
        log.info("processing... saving company ${companyRequest.name}")

        companyRepository.save(companyRequest)

        log.info("company saved ${companyRequest.id}")

        return HttpResponse.created(companyRequest)
    }


    @Get("/{id}")
    fun getCompany(id: Long): HttpResponse<Any> {
        log.info("processing... geting company by id $id")

        val company = companyRepository
            .findById(id)
            .orElse(null)

        log.info("company returned id $id")

        return HttpResponse.ok(company)
    }

    @Put("/{id}")
    fun updateCompany(id: Long, companyRequest: Company): HttpResponse<Any> {
        println("processing... updating company $id")

        val company = companyRepository
            .findById(id)
            .orElse(null)

        company.name = companyRequest.name

        companyRepository.update(company)

        println("processing... updated company $id")

        return HttpResponse.ok(company)
    }


    @Delete("/{id}")
    fun updateCompany(id: Long): HttpResponse<Any> {
        println("processing... delete company")
        val company = companyRepository
            .findById(id)
            .orElse(null)

        companyRepository.delete(company)

        println("company deleted")

        return HttpResponse.ok()
    }

}