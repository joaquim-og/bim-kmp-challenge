package co.bimm.challenge.sakeShops.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bimm_kmp_challenge_joaquim.shared.generated.resources.Res
import bimm_kmp_challenge_joaquim.shared.generated.resources.of_5_stars
import org.jetbrains.compose.resources.stringResource

@Composable
fun SakeShopHeader(
    name: String,
    rating: Double
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StarRating(
                rating = rating,
                starSize = 20f
            )
            Text(
                text = "$rating ${stringResource(Res.string.of_5_stars)}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}