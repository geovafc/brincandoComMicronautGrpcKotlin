package com.agiledeveloper.stockprices.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Company(@Id @GeneratedValue var id: Long, var name: String)
