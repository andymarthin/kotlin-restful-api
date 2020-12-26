package id.web.marthin.kotlinrestfulapi.model

data class WebResponse<T> (
        val code: Int,

        val status: String,

        val data: T
)