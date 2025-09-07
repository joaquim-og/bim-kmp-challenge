package co.bimm.challenge.sakeShops.presentation.sakeShopDetail

import androidx.compose.foundation.ScrollState
import co.bimm.challenge.sakeShops.presentation.components.BackArrowIconButton
import co.bimm.challenge.sakeShops.presentation.components.SakeShopAddressSection
import co.bimm.challenge.sakeShops.presentation.components.SakeShopDescriptionSection
import co.bimm.challenge.sakeShops.presentation.components.SakeShopHeader
import co.bimm.challenge.sakeShops.presentation.components.SakeShopImage
import co.bimm.challenge.sakeShops.presentation.components.SakeShopWebsiteSection
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.bimm.challenge.sakeShops.data.models.SakeShop
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SakeShopDetailScreen(
    modifier: Modifier = Modifier,
    sakeShop: SakeShop?,
    onBackClicked: () -> Unit,
    onAddressClicked: (String) -> Unit,
    onWebsiteClicked: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    SakeShopDetailsView(
        modifier = modifier,
        sakeShop = sakeShop,
        scrollState = scrollState,
        onBackClicked = onBackClicked,
        onAddressClicked = onAddressClicked,
        onWebsiteClicked = onWebsiteClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SakeShopDetailsView(
    modifier: Modifier,
    sakeShop: SakeShop?,
    scrollState: ScrollState,
    onBackClicked: () -> Unit,
    onAddressClicked: (String) -> Unit,
    onWebsiteClicked: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = sakeShop?.name.orEmpty(),
                        maxLines = 1,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    BackArrowIconButton(
                        onClick = onBackClicked,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            SakeShopImage(
                imageUrl = sakeShop?.picture,
                shopName = sakeShop?.name.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                SakeShopHeader(
                    name = sakeShop?.name.orEmpty(),
                    rating = sakeShop?.rating ?: 0.0
                )

                SakeShopDescriptionSection(
                    description = sakeShop?.shopDescription.orEmpty()
                )

                SakeShopAddressSection(
                    address = sakeShop?.address.orEmpty(),
                    onAddressClicked = { onAddressClicked(sakeShop?.address.orEmpty()) }
                )

                sakeShop?.website?.let { website ->
                    SakeShopWebsiteSection(
                        website = website,
                        onWebsiteClicked = { onWebsiteClicked(website) }
                    )
                }
            }
        }
    }
}

// Previews
@Composable
@Preview
private fun SakeShopDetailScreenPreview() {
    MaterialTheme {
        val sampleShop = SakeShop(
            name = "信州スシサカバ 寿しなの",
            shopDescription = "A traditional sushi bar specializing in fresh sashimi and an extensive collection of premium sake from the Nagano region. Our master chef has over 20 years of experience and carefully selects the finest ingredients.",
            picture = "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
            rating = 4.5,
            address = "〒380-0824 長野県長野市南長野南石堂町1421",
            coordinates = listOf(36.644257, 138.18668),
            googleMapsLink = "https://maps.app.goo.gl/4fYMDSfNd6ocsDwt6",
            website = "https://www.sushinano.com/"
        )

        SakeShopDetailScreen(
            sakeShop = sampleShop,
            onBackClicked = {},
            onAddressClicked = {},
            onWebsiteClicked = {}
        )
    }
}

@Composable
@Preview
private fun SakeShopDetailScreenNoImagePreview() {
    MaterialTheme {
        val sampleShop = SakeShop(
            name = "Hokuan Brewery",
            shopDescription = "Small family-owned brewery in Omachi City, known for its Daikokumasamune sake. We use traditional brewing methods passed down through generations.",
            picture = null,
            rating = 4.0,
            address = "〒398-0002 長野県大町市大町2340-1",
            coordinates = listOf(36.648273, 138.18724),
            googleMapsLink = "https://maps.app.goo.gl/GQrMNVo6YoVEvCPU6",
            website = null
        )

        SakeShopDetailScreen(
            sakeShop = sampleShop,
            onBackClicked = {},
            onAddressClicked = {},
            onWebsiteClicked = {}
        )
    }
}