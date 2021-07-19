package com.example.loginapp.server

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://10.0.2.2:3000" // server address

// Retrofit에서 JSON 응답을 가져와 반환
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build() // Retrofit 객체 생성

// Retrofit 객체에서 create() 함수를 호출하는 데는 리소스가 많이 들고,
// 앱에는 Retrofit API 서비스의 인스턴스가 하나만 필요합니다.
// 따라서 객체 선언을 사용하여 나머지 앱의 나머지 부분에 서비스를 노출합니다.
object RetrofitApi {
    //  '지연 인스턴스화'는 실제로 객체가 필요할 때까지는 불필요한 계산이 실행되거나 다른 컴퓨팅 리소스가 사용되지 않도록 하기 위해 객체 생성을 의도적으로 지연하는 것입니다.
    val retrofitService: RetrofitInterface by lazy{ // 최초 사용시 초기화하기 위해 지연속성을 사용
        retrofit.create(RetrofitInterface::class.java)
    }
}

interface RetrofitInterface {
    @FormUrlEncoded
    @POST("/login")
    fun executeLogin(@FieldMap params: HashMap<String, String>): Call<UserInfo>

    @FormUrlEncoded
    @POST("/signup")
    fun executeSignup(@FieldMap params: HashMap<String, String>): Call<Void>
}