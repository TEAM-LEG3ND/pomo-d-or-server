package com.leg3nd.domain.exception

class BaseException(type: Type, errorMessage: String) : Exception(errorMessage) {
    enum class Type {
        BAD_REQUEST,
        INTERNAL_ERROR,
    }
}
