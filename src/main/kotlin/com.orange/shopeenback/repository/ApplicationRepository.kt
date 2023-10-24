package com.orange.shopeenback.repository

import io.micronaut.data.mongodb.annotation.MongoFindQuery
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import org.bson.types.ObjectId
import com.orange.shopeenback.dto.ApplicationDTO

@MongoRepository
interface ApplicationRepository : CrudRepository<ApplicationDTO, ObjectId>{
    @MongoFindQuery("{idExtName: :name}")
    fun findApplicationDTOByIdExtName(name: String): List<ApplicationDTO>
}