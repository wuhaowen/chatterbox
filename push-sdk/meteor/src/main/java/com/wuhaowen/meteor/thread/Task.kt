package com.wuhaowen.meteor.thread

enum class TaskPriority {
    LOW,
    MEDIUM,
    HIGH
}

interface Task : Comparable<Task>{
    var priority: TaskPriority

    override fun compareTo(other: Task): Int = this.priority.ordinal - other.priority.ordinal

}