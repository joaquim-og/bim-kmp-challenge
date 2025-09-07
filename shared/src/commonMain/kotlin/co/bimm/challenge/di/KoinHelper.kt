package co.bimm.challenge.di

import co.bimm.challenge.core.platform.PlatformActions
import co.bimm.challenge.sakeShops.presentation.sakeShopDetail.SakeShopDetailViewModel
import co.bimm.challenge.sakeShops.presentation.sakeShopList.SakeShopListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper : KoinComponent {

    fun getSakeShopListViewModel(): SakeShopListViewModel {
        val viewModel: SakeShopListViewModel by inject()
        return viewModel
    }

    fun getSakeShopDetailViewModel(): SakeShopDetailViewModel {
        val viewModel: SakeShopDetailViewModel by inject()
        return viewModel
    }

    fun getPlatformActions(): PlatformActions {
        val platformActions: PlatformActions by inject()
        return platformActions
    }
}