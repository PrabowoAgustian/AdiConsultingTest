package com.dirumahajanews.constant

class HttpResponse {
    companion object {
        const val httpError = "HTTP_ERROR"
        const val connectionError = "CONNECTION_ERROR"
        const val unAuthorizeError = "UN_AUTHORIZE_ERROR"
        const val resultOk = "OK"

        const val badRequest = 400
        const val unAuthorize = 401
        const val notFound = 404
        const val methodNotAllowed = 405
        const val internalServerError = 500
        const val badGateway = 502
        const val serviceUnavailable = 503
    }
}