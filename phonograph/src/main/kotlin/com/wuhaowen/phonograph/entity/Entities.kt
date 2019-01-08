package com.wuhaowen.phonograph.entity

import org.joda.time.DateTime
import javax.persistence.*
import java.io.Serializable

@Entity
@Table(name = "t_messages")
data class Message(@Id @GeneratedValue var id: Long = 0,
                   @Column var msgId: Long  = 0,
                   @Column var tag: String? = null,
                   @Column var msg: String = "",
                   @Column var userId: String = "",
                   @Column var fromUser: String = "",
                   @Column var createTime: DateTime? = null): Serializable

@Entity
@Table(name = "t_user_tag")
data class UserTag(
        @Id @GeneratedValue var id:Long = 0,
        @Column var userId:String = "",
        @Column var tag: String = "",
        @Column var lastTime: DateTime? = null

)