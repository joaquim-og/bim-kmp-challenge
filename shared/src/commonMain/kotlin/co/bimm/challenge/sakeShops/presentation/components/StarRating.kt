package co.bimm.challenge.sakeShops.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin

@Composable
fun StarRating(
    rating: Double,
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
    starSize: Float = 24f
) {
    val filledStars = floor(rating).toInt()
    val hasHalfStar = rating - filledStars >= 0.5
    val filledColor = MaterialTheme.colorScheme.primary
    val emptyColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        repeat(maxRating) { index ->
            val starType = when {
                index < filledStars -> StarType.Filled
                index == filledStars && hasHalfStar -> StarType.Half
                else -> StarType.Empty
            }

            DrawableStar(
                starType = starType,
                size = starSize,
                filledColor = filledColor,
                emptyColor = emptyColor
            )
        }
    }
}

@Composable
private fun DrawableStar(
    starType: StarType,
    size: Float,
    filledColor: Color,
    emptyColor: Color,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier.size(size.dp)
    ) {
        val canvasSize = this.size.minDimension
        val center = Offset(canvasSize / 2, canvasSize / 2)
        val radius = canvasSize / 2 * 0.9f
        when (starType) {
            StarType.Filled -> {
                drawStar(
                    center = center,
                    radius = radius,
                    color = filledColor,
                    filled = true
                )
            }
            StarType.Half -> {
                drawStar(
                    center = center,
                    radius = radius,
                    color = emptyColor,
                    filled = false
                )
                drawHalfStar(
                    center = center,
                    radius = radius,
                    color = filledColor
                )
            }
            StarType.Empty -> {
                drawStar(
                    center = center,
                    radius = radius,
                    color = emptyColor,
                    filled = false
                )
            }
        }
    }
}

private fun DrawScope.drawStar(
    center: Offset,
    radius: Float,
    color: Color,
    filled: Boolean = true
) {
    val path = createStarPath(center, radius)

    if (filled) {
        drawPath(
            path = path,
            color = color,
            style = Fill
        )
    } else {
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 1.dp.toPx())
        )
    }
}

private fun DrawScope.drawHalfStar(
    center: Offset,
    radius: Float,
    color: Color
) {
    val path = createStarPath(center, radius)

    val clipPath = Path().apply {
        addRect(
            Rect(
                left = 0f,
                top = 0f,
                right = center.x,
                bottom = size.height
            )
        )
    }

    clipPath(clipPath) {
        drawPath(
            path = path,
            color = color,
            style = Fill
        )
    }
}

private fun createStarPath(center: Offset, radius: Float): Path {
    val path = Path()
    val outerRadius = radius
    val innerRadius = radius * 0.38f
    var angle = -PI / 2.0

    val firstX = center.x + cos(angle).toFloat() * outerRadius
    val firstY = center.y + sin(angle).toFloat() * outerRadius
    path.moveTo(firstX, firstY)

    for (i in 1 until 10) {
        angle += PI / 5.0
        val currentRadius = if (i % 2 == 1) innerRadius else outerRadius
        val x = center.x + cos(angle).toFloat() * currentRadius
        val y = center.y + sin(angle).toFloat() * currentRadius
        path.lineTo(x, y)
    }

    path.close()
    return path
}

private enum class StarType {
    Filled,
    Half,
    Empty
}

@Composable
@Preview
private fun StarRatingPreview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StarRating(rating = 0.0)
        StarRating(rating = 1.5)
        StarRating(rating = 2.5)
        StarRating(rating = 3.0)
        StarRating(rating = 4.5)
        StarRating(rating = 5.0)
    }
}