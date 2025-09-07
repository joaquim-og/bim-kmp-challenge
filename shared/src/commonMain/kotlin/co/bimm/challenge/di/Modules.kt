package co.bimm.challenge.di

import co.bimm.challenge.core.data.HttpClientFactory
import co.bimm.challenge.sakeShops.data.network.KtorRemoteSakeShopDataSource
import co.bimm.challenge.sakeShops.data.network.RemoteSakeShopDataSource
import co.bimm.challenge.sakeShops.data.repository.SearchSakesShopRepositoryImpl
import co.bimm.challenge.sakeShops.domain.SearchSakesShopRepository
import co.bimm.challenge.sakeShops.presentation.sakeShopDetail.SakeShopDetailViewModel
import co.bimm.challenge.sakeShops.presentation.sakeShopList.SakeShopListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }

    single<RemoteSakeShopDataSource> {
        KtorRemoteSakeShopDataSource(
            httpClient = get(),
            useMockData = true
        )
    }

    single<SearchSakesShopRepository> {
        SearchSakesShopRepositoryImpl(
            remoteSakeShopDataSource = get()
        )
    }


    viewModelOf(::SakeShopListViewModel)
    viewModelOf(::SakeShopDetailViewModel)
}