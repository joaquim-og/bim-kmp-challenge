package co.bimm.challenge.di

import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.Module
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
    }