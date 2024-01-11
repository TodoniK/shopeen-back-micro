package com.orange.shopeenback.repository

import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import org.bson.types.ObjectId
import com.orange.shopeenback.model.Configuration

@MongoRepository
interface ConfigurationRepository : CrudRepository<Configuration, ObjectId>{
}