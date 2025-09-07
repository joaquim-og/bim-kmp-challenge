package co.bimm.challenge.sakeShops.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.bimm.challenge.sakeShops.data.models.SakeShop
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SakeShopListItem(
    sakeShop: SakeShop,
    onSakeShopTapped: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = onSakeShopTapped,
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val painter = rememberAsyncImagePainter(
                model = sakeShop.picture,
                onError = { error ->
                    error.result.throwable.printStackTrace()
                }
            )

            val painterState by painter.state.collectAsStateWithLifecycle()
            val transition by animateFloatAsState(
                targetValue = if (painterState is AsyncImagePainter.State.Success) {
                    1f
                } else {
                    0f
                },
                animationSpec = tween(durationMillis = 800),
                label = "image_transition"
            )

            Image(
                painter = painter,
                contentDescription = "Picture of ${sakeShop.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .graphicsLayer {
                        rotationX = (1f - transition) * 30f
                        val scale = 0.8f + (0.2f * transition)
                        scaleX = scale
                        scaleY = scale
                    }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = sakeShop.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = sakeShop.address,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StarRating(
                        rating = sakeShop.rating,
                        starSize = 16f,
                        modifier = Modifier
                    )

                    Text(
                        text = sakeShop.rating.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

// Previews
@Composable
@Preview
private fun SakeShopListItemPreview() {
    MaterialTheme {
        val sampleSakeShop = SakeShop(
            name = "信州スシサカバ 寿しなの",
            shopDescription = "Sushi bar with a variety of sake options.",
            picture = "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
            rating = 4.5,
            address = "〒380-0824 長野県長野市南長野南石堂町1421",
            coordinates = listOf(36.644257, 138.18668),
            googleMapsLink = "https://maps.app.goo.gl/4fYMDSfNd6ocsDwt6",
            website = "https://www.sushinano.com/"
        )

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SakeShopListItem(
                sakeShop = sampleSakeShop,
                onSakeShopTapped = {}
            )

            SakeShopListItem(
                sakeShop = sampleSakeShop.copy(
                    name = "Masuichi Brewery",
                    rating = 4.3,
                    picture = null
                ),
                onSakeShopTapped = {}
            )
        }
    }
}

@Composable
@Preview
private fun SakeShopListItemLongNamePreview() {
    MaterialTheme {
        val sampleSakeShop = SakeShop(
            name = "Very Long Sake Shop Name That Should Be Truncated With Ellipsis",
            shopDescription = "This is a brewery with a really long description that should also be truncated properly",
            picture = null,
            rating = 3.8,
            address = "〒381-0201 長野県上高井郡小布施町大字小布施807番地の長い住所",
            coordinates = listOf(36.648273, 138.18724),
            googleMapsLink = "https://maps.app.goo.gl/example",
            website = "https://www.example.com/"
        )

        SakeShopListItem(
            sakeShop = sampleSakeShop,
            onSakeShopTapped = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}