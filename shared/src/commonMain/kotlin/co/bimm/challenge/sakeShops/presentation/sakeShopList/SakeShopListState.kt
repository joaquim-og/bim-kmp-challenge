package co.bimm.challenge.sakeShops.presentation.sakeShopList

import co.bimm.challenge.core.presentation.UiText
import co.bimm.challenge.sakeShops.data.models.SakeShop

data class SakeShopListState(
    val sakeShopsList: List<SakeShop> = emptyList(),
    val isLoading: Boolean = true,
    val selectedSakeShop: SakeShop? = null,
    val errorMessage: UiText? = null
)