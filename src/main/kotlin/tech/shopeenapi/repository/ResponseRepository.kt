package tech.shopeenapi.repository

import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import tech.shopeenapi.dto.ResponseDTO

@MongoRepository
interface ResponseRepository : CrudRepository<ResponseDTO, String>