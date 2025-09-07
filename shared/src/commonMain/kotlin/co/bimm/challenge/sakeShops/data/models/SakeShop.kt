package co.bimm.challenge.sakeShops.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SakeShop(
    val name: String,
    val description: String,
    val picture: String?,
    val rating: Double,
    val address: String,
    val coordinates: List<Double>,
    @SerialName("google_maps_link")
    val googleMapsLink: String,
    val website: String?
)