package co.bimm.challenge.sakeShops.domain

import co.bimm.challenge.core.domain.DataError
import co.bimm.challenge.core.domain.Result
import co.bimm.challenge.sakeShops.data.models.SakeShop

interface SearchSakesShopRepository {
    suspend fun searchSakesShops(queryLimit: Int?): Result<List<SakeShop>, DataError.Remote>
}