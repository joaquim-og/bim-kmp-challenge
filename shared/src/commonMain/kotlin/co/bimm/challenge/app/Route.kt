package co.bimm.challenge.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object SakeShopsGraph: Route

    @Serializable
    data object SakeShopsList: Route

    @Serializable
    data class SakeShopDetail(val sakeShopName: String): Route
}