package com.github.muneebwanee.dash.data

interface CallApi {
     @POST("asdf")
     fun pushCall(@Body request: CallRequest) : Response
}