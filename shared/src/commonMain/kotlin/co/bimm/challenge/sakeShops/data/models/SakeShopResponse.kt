package co.bimm.challenge.sakeShops.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SakeShopResponse(
    val data: List<SakeShop>
)
