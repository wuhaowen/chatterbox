package com.wuhaowen.operator.dao

import com.wuhaowen.operator.entity.RouteRule
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RouteMongoRepository: MongoRepository<RouteRule, ObjectId>
