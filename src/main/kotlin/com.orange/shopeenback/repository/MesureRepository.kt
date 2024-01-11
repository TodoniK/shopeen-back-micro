package com.orange.shopeenback.repository

import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import org.bson.types.ObjectId
import com.orange.shopeenback.model.Mesure

@MongoRepository
interface MesureRepository : CrudRepository<Mesure, ObjectId>{
    fun findByApplicationId(applicationId: ObjectId): List<Mesure>
}