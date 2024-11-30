package com.software.pick2flick.authserver.exceptions

class RoleNotFoundException(
    customMessage: String
): RuntimeException(customMessage)