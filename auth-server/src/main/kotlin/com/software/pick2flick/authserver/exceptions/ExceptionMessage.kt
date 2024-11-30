package com.software.pick2flick.authserver.exceptions

class ExceptionMessage {
    companion object {
        const val USER_IS_ALREADY_REGISTERED_MESSAGE = "{\"error\": " +
                "\"user with the same credentials is already registered.\"}"
        const val METHOD_NOT_SUPPORTED_MESSAGE: String = " method is not supported.\"}"
        const val INVALID_JSON_FORMAT: String = "{\"error\": \"invalid json format.\"}"
        const val FAILED_REQUEST_TO_REMOTE_SERVER_MESSAGE = "{\"error\": \"failed request to remote server.\"}"
        const val ROLE_NOT_FOUND_MESSAGE = " no found.\"}"
        const val USER_NOT_FOUND = "{\"error\": \"user not found.\"}"
    }
}