package co.bimm.challenge.di

import co.bimm.challenge.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }

    //TODO
    // remote Repository fetch

}