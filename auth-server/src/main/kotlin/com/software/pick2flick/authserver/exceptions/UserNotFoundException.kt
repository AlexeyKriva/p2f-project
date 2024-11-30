package com.software.pick2flick.authserver.exceptions

class UserNotFoundException(
    customMessage: String
): RuntimeException(customMessage)