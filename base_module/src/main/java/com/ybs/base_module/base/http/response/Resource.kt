package com.ybs.base_module.base.http.response

/**
 * 将网络调用的状态传给UI层
 * @param message 可以是简单的字符串，如果业务复杂需要用状态码，可以使用Int，甚至于Pair,Triple
 */
class Resource<out T>(val status: Status, val data: T?, val message: Any?) {
    companion object {
        fun <T> success(data: T?, msg: Any? = null) = Resource(
            Status.SUCCESS,
            data,
            msg
        )

        fun <T> error(msg: Any?, data: T? = null) = Resource(
            Status.ERROR,
            data,
            msg
        )

        fun <T> loading(msg: Any? = null, data: T? = null) = Resource(
            Status.LOADING,
            data,
            msg
        )
    }

    override fun toString(): String {
        return "Resource(status=$status, message=$message, data=$data)"
    }


}