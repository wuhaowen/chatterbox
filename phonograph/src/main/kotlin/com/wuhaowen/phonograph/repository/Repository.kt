package com.wuhaowen.phonograph.repository

import com.wuhaowen.phonograph.entity.Message
import com.wuhaowen.phonograph.entity.UserTag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface MessageRepository: JpaRepository<Message, Long>

@Repository
@Transactional
interface UserTagRepository: JpaRepository<UserTag, Long>{

    @Query("select t.userId from t_user_tag t where t.tag = :tagName")
    fun findUsersByTag(@Param("tagName") tagName: String) :List<String>

}