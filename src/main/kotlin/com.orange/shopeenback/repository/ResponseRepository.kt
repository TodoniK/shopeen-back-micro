package com.orange.shopeenback.repository

import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import com.orange.shopeenback.dto.ResponseDTO

@MongoRepository
interface ResponseRepository : CrudRepository<ResponseDTO, String>