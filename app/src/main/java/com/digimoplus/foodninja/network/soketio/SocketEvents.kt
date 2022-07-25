package com.digimoplus.foodninja.network.soketio

enum class SocketEvents(val event: String) {
    UPDATE_CHAT("updateChat"),
    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe"),
    NEW_MESSAGE("newMessage"),
    NEW_USER("newUserToChatRoom"),
    USER_LEFT("userLeftChatRoom"),
    START_TYPE_ING("typing"),
    STOP_TYPE_ING("stopTyping"),
}