package co.bimm.challenge.sakeShops.presentation.sakeShopList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.bimm.challenge.core.domain.onError
import co.bimm.challenge.core.domain.onSuccess
import co.bimm.challenge.core.presentation.toUiText
import co.bimm.challenge.sakeShops.domain.SearchSakesShopRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SakeShopListViewModel(
    private val searchSakesShopRepository: SearchSakesShopRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SakeShopListState())
    val state = _state
        .onStart {
            fetchSakeShops()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Companion.WhileSubscribed(5000L),
            _state.value
        )

    private fun fetchSakeShops() = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        searchSakesShopRepository
            .searchSakesShops(null)
            .onSuccess { result ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        sakeShopsList = result
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        sakeShopsList = emptyList(),
                        isLoading = false,
                        errorMessage = error.toUiText()
                    )
                }
            }
    }

}