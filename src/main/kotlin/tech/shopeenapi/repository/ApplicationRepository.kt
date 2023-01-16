package tech.shopeenapi.repository

import io.micronaut.data.mongodb.annotation.MongoFindQuery
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import org.bson.types.ObjectId
import tech.shopeenapi.entity.Application

@MongoRepository
interface ApplicationRepository : CrudRepository<Application, ObjectId>{
    @MongoFindQuery("{idExtName: :name}")
    fun findApplicationByIdExtName(name: String): List<Application>
}