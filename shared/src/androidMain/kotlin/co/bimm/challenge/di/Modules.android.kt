package co.bimm.challenge.di

import co.bimm.challenge.core.platform.PlatformActions
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.Module
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
        single { PlatformActions(androidContext()) }
    }