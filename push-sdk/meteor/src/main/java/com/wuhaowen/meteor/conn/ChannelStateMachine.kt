package com.wuhaowen.meteor.conn

sealed class State {
    object Start: State()
    object Connecting: State()
    object Connected: State()
    object Message: State()


}