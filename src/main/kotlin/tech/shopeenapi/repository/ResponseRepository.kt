package tech.shopeenapi.repository

import tech.shopeenapi.entity.Response
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository

@MongoRepository
interface ResponseRepository : CrudRepository<Response, String>