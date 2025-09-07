package co.bimm.challenge.sakeShops.presentation.sakeShopDetail

import androidx.lifecycle.ViewModel
import co.bimm.challenge.sakeShops.data.models.SakeShop
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SakeShopDetailViewModel: ViewModel() {

    private val _selectedSakeShop = MutableStateFlow<SakeShop?>(null)
    val selectedSakeShop = _selectedSakeShop.asStateFlow()

    fun onSelectSakeShop(sakeShop: SakeShop?) {
        _selectedSakeShop.value = sakeShop
    }
}