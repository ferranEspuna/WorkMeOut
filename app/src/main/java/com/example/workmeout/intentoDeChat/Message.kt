package com.example.workmeout.intentoDeChat

class Message (var text: String, var memberData: MemberData, var belongsToCurrentUser: Boolean) {

    fun isBelongsToCurrentUser() : Boolean {
        return this.belongsToCurrentUser
    }
}