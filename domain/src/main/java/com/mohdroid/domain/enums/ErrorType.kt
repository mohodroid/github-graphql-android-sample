package com.mohdroid.domain.enums

enum class ErrorType(val value: Int) {

    IO_EXCEPTION(1_001),
    CANCELED_EXCEPTION(1_002),
    HTTP_EXCEPTION(1_004),
    PARSE_EXCEPTION(1_005),
    EXCEPTION(1_006),
    INVALID_TOKEN_EXCEPTION(1_008),
    JSON_EXCEPTION(1_009),

   INVALID_COUNT_NUMBER(2_001)
}