package com.dirumahajanews.pojo.common

class ResponseSource {

    var status: Status
    var error: Throwable? = null
    var type: String? = null
    var source: Any? = null

    private constructor(
        status: Status,
        error: Throwable?
    ) {
        this.status = status
        this.error = error
    }

    constructor(status: Status, source: Any?) {
        this.status = status
        this.source = source
        type = null
    }

    constructor(
        status: Status,
        articles: Any?,
        type: String?
    ) {
        this.status = status
        this.source = source
        this.type = type
    }

    companion object {
        fun loading(): Response {
            return Response(
                Status.LOADING,
                ""
            )
        }

        fun loading(type: String?): Response {
            return Response(
                Status.LOADING,
                "",
                type
            )
        }

        fun success(data: Any?): Response {
            return Response(
                Status.SUCCESS,
                data
            )
        }

        fun success(
            data: Any?,
            type: String?
        ): Response {
            return Response(
                Status.SUCCESS,
                data,
                type
            )
        }

        fun error(error: Any): Response {
            return Response(
                Status.ERROR,
                error
            )
        }

        fun error(
            error: Any?,
            type: String?
        ): Response {
            return Response(
                Status.ERROR,
                error,
                type
            )
        }

        fun error(error: Throwable): Response {
            return Response(
                Status.ERROR,
                error
            )
        }
    }
}