package co.bimm.challenge.sakeShops.data.network

import co.bimm.challenge.core.domain.Result
import co.bimm.challenge.core.domain.DataError
import co.bimm.challenge.sakeShops.data.models.SakeShopResponse


interface RemoteSakeShopDataSource {

    suspend fun searchSakesShops(
        resultLimit: Int? = null
    ): Result<SakeShopResponse, DataError.Remote>
}