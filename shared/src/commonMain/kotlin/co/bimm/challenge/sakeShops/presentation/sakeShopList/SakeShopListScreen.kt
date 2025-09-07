package co.bimm.challenge.sakeShops.presentation.sakeShopList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.bimm.challenge.sakeShops.data.models.SakeShop
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SakeShopListScreen(
    viewModel: SakeShopListViewModel = koinViewModel(),
    onSakeShopTapped: (SakeShop) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SakeShopListView(
        state = state,
        onSakeShopTapped = onSakeShopTapped
    )
}


@Composable
fun SakeShopListView(
    state: SakeShopListState,
    onSakeShopTapped: (SakeShop) -> Unit
) {
    val sakeShopListState = rememberLazyListState()

    LaunchedEffect(state.sakeShopsList) {
        sakeShopListState.animateScrollToItem(0)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "${state.sakeShopsList}"
        )
    }
}