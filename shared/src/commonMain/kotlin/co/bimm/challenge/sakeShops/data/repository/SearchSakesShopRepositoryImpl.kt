package co.bimm.challenge.sakeShops.data.repository

import co.bimm.challenge.core.domain.Result
import co.bimm.challenge.core.domain.DataError
import co.bimm.challenge.core.domain.map
import co.bimm.challenge.sakeShops.data.models.SakeShop
import co.bimm.challenge.sakeShops.data.network.RemoteSakeShopDataSource
import co.bimm.challenge.sakeShops.domain.SearchSakesShopRepository

class SearchSakesShopRepositoryImpl(
    private val remoteSakeShopDataSource: RemoteSakeShopDataSource
) : SearchSakesShopRepository {
    override suspend fun searchSakesShops(queryLimit: Int?): Result<List<SakeShop>, DataError.Remote> {
        return remoteSakeShopDataSource
            .searchSakesShops(queryLimit)
            .map { sakeShopResponse ->
                sakeShopResponse.data
            }
    }

}