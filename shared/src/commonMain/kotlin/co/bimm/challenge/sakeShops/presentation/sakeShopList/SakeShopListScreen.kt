package co.bimm.challenge.sakeShops.presentation.sakeShopList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bimm_kmp_challenge_joaquim.shared.generated.resources.Res
import bimm_kmp_challenge_joaquim.shared.generated.resources.empty_list
import bimm_kmp_challenge_joaquim.shared.generated.resources.error_Retry
import bimm_kmp_challenge_joaquim.shared.generated.resources.loading
import bimm_kmp_challenge_joaquim.shared.generated.resources.title_list
import co.bimm.challenge.sakeShops.data.models.SakeShop
import co.bimm.challenge.sakeShops.presentation.components.SakeShopListItem
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SakeShopListScreen(
    viewModel: SakeShopListViewModel = koinViewModel(),
    onSakeShopTapped: (SakeShop) -> Unit,
    onRetry: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SakeShopListView(
        state = state,
        onSakeShopTapped = onSakeShopTapped,
        onRetry = onRetry
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SakeShopListView(
    state: SakeShopListState,
    onSakeShopTapped: (SakeShop) -> Unit,
    onRetry: () -> Unit
) {
    val sakeShopListState = rememberLazyListState()

    LaunchedEffect(state.sakeShopsList) {
        if (state.sakeShopsList.isNotEmpty()) {
            sakeShopListState.animateScrollToItem(0)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.title_list),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> {
                    LoadingState()
                }

                state.errorMessage != null -> {
                    ErrorState(
                        message = state.errorMessage.asString(),
                        onRetry = onRetry
                    )
                }

                state.sakeShopsList.isEmpty() -> {
                    EmptyState()
                }

                else -> {
                    LazyColumn(
                        state = sakeShopListState,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            items = state.sakeShopsList,
                            key = { it.name }
                        ) { sakeShop ->
                            SakeShopListItem(
                                sakeShop = sakeShop,
                                onSakeShopTapped = { onSakeShopTapped(sakeShop) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(Res.string.loading),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Button(
                onClick = onRetry,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(stringResource(Res.string.error_Retry))
            }
        }
    }
}

@Composable
private fun EmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = stringResource(Res.string.empty_list),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
private fun SakeShopListScreenEmptyPreview() {
    MaterialTheme {
        SakeShopListView(
            state = SakeShopListState(
                isLoading = false,
                sakeShopsList = emptyList()
            ),
            onSakeShopTapped = {},
            onRetry = {}
        )
    }
}

@Composable
@Preview
private fun SakeShopListScreenWithDataPreview() {
    MaterialTheme {
        val sampleShops = listOf(
            SakeShop(
                name = "信州スシサカバ 寿しなの",
                description = "Sushi bar with a variety of sake options.",
                picture = "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
                rating = 4.0,
                address = "〒380-0824 長野県長野市南長野南石堂町1421",
                coordinates = listOf(36.644257, 138.18668),
                googleMapsLink = "https://maps.app.goo.gl/4fYMDSfNd6ocsDwt6",
                website = "https://www.sushinano.com/"
            ),
            SakeShop(
                name = "Masuichi Brewery",
                description = "Brewery located in Obuse, known for its traditional sake.",
                picture = "https://gigaplus.makeshop.jp/masuichi/assets_v2/img/common/slide1.jpg",
                rating = 4.3,
                address = "〒381-0201 長野県上高井郡小布施町大字小布施807",
                coordinates = listOf(36.648273, 138.18724),
                googleMapsLink = "https://maps.app.goo.gl/qrZekaJq8qP3yMiy7",
                website = "https://www.masuichi.com/"
            ),
            SakeShop(
                name = "Tamamura Honten",
                description = "Brewery in Yamanouchi, known for its sake and beer.",
                picture = null,
                rating = 4.6,
                address = "〒381-0401 長野県下高井郡山ノ内町大字平穏1163",
                coordinates = listOf(36.648273, 138.18724),
                googleMapsLink = "https://maps.app.goo.gl/htbv7LKDc9N3aHZ9A",
                website = "http://www.tamamura-honten.co.jp/"
            )
        )

        SakeShopListView(
            state = SakeShopListState(
                isLoading = false,
                sakeShopsList = sampleShops
            ),
            onSakeShopTapped = {},
            onRetry = {}
        )
    }
}