package com.software.pick2flick.authserver.exceptions

class UserIsAlreadyRegisteredException(
    customMessage: String
): RuntimeException(customMessage)