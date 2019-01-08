package com.wuhaowen.operator.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


/**
 * @program: operator
 * @description: 路由信息
 * @author: wuhaowen
 * @create: 2018-11-2018/11/2
 **/
@Document(collection = "route_rule")
data class RouteRule(@Id var id : ObjectId, var tweetId: String, var userId: String)