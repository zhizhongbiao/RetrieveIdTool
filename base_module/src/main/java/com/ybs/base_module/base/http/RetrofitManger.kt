package com.ybs.base_module.base.http

import com.google.gson.GsonBuilder
import com.ybs.base_module.BuildConfig
import com.ybs.base_module.base.http.request.MyInterceptor
import e
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 *
 * @ProjectName: RetrieveIdTool
 * @Package: com.ybs.base_module.base.http
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/4/20 17:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/20 17:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class RetrofitManger private constructor() {


    companion object {
        val gson =
            GsonBuilder()
                .setLenient()
                .create()
        val inst = RetrofitManger()
    }

    init {
        initRetrofitManager()
    }


    private lateinit var retrofit: Retrofit

    private fun initRetrofitManager() {
        var isHttps: Boolean = BuildConfig.BASE_URL?.contains("https", true)
        retrofit = Retrofit.Builder()
//            .baseUrl(BuildConfig.BASE_URL)
            .baseUrl(getUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpClient().newBuilder().apply {

                if (isHttps) {
                    adaptToHttps(this)
                }

                connectTimeout(15, TimeUnit.SECONDS)
                writeTimeout(15, TimeUnit.SECONDS)
                readTimeout(15, TimeUnit.SECONDS)
                val logger = HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logger)
                addInterceptor(MyInterceptor())
            }.build())
            .build()
    }

    private fun adaptToHttps(builder: OkHttpClient.Builder) {
        try {
            val sslInstance = SSLContext.getInstance("SSL")
            sslInstance.init(null, arrayOf<TrustManager>(MyTrustMgr()), SecureRandom())
            builder.sslSocketFactory(sslInstance.socketFactory, object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
            })

            builder.hostnameVerifier { _, _ ->
                return@hostnameVerifier true
            }
        } catch (e: Exception) {
            e(" e.msg  = ${e.message}")
        }
    }

    fun <S> createService(clz: Class<S>): S = retrofit.create(clz)

    inline fun <reified T> createInlineService(clazz: Class<T>): T =
        createService(clazz)


    private inner class MyTrustMgr() : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

        }

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {

        }

        override fun getAcceptedIssuers() = null
    }
}