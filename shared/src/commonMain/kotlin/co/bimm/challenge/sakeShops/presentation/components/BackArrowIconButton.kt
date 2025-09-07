package co.bimm.challenge.sakeShops.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BackArrowIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        BackArrowIcon(
            tint = tint,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun BackArrowIcon(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Canvas(modifier = modifier) {
        drawBackArrow(
            color = tint,
            strokeWidth = 2.dp.toPx()
        )
    }
}

private fun DrawScope.drawBackArrow(
    color: Color,
    strokeWidth: Float
) {
    val canvasWidth = size.width
    val canvasHeight = size.height
    val centerY = canvasHeight / 2

    val arrowLength = canvasWidth * 0.7f
    val arrowHeadLength = canvasWidth * 0.3f
    val startX = canvasWidth * 0.15f
    val endX = startX + arrowLength

    val stroke = Stroke(
        width = strokeWidth,
        cap = StrokeCap.Round
    )

    drawLine(
        color = color,
        start = Offset(startX, centerY),
        end = Offset(endX, centerY),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )

    val arrowHeadAngle = 45f
    val arrowHeadX = startX + arrowHeadLength
    val arrowHeadOffsetY = arrowHeadLength * 0.5f

    drawLine(
        color = color,
        start = Offset(startX, centerY),
        end = Offset(arrowHeadX, centerY - arrowHeadOffsetY),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )

    drawLine(
        color = color,
        start = Offset(startX, centerY),
        end = Offset(arrowHeadX, centerY + arrowHeadOffsetY),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}

@Composable
fun BackArrowIconFilled(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Canvas(modifier = modifier) {
        drawBackArrowFilled(color = tint)
    }
}

private fun DrawScope.drawBackArrowFilled(color: Color) {
    val canvasWidth = size.width
    val canvasHeight = size.height
    val centerY = canvasHeight / 2

    val arrowBodyWidth = canvasWidth * 0.5f
    val arrowBodyHeight = canvasWidth * 0.15f
    val arrowHeadWidth = canvasWidth * 0.4f
    val arrowHeadHeight = canvasWidth * 0.4f

    val path = Path().apply {
        moveTo(0f, centerY)

        lineTo(arrowHeadWidth, centerY - arrowHeadHeight / 2)

        lineTo(arrowHeadWidth, centerY - arrowBodyHeight / 2)

        lineTo(canvasWidth, centerY - arrowBodyHeight / 2)
        lineTo(canvasWidth, centerY + arrowBodyHeight / 2)

        lineTo(arrowHeadWidth, centerY + arrowBodyHeight / 2)

        lineTo(arrowHeadWidth, centerY + arrowHeadHeight / 2)

        lineTo(0f, centerY)

        close()
    }

    drawPath(
        path = path,
        color = color
    )
}

// Previews
@Composable
@Preview
private fun BackArrowIconButtonPreview() {
    MaterialTheme {
        BackArrowIconButton(
            onClick = {},
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
@Preview
private fun BackArrowIconPreview() {
    MaterialTheme {
        BackArrowIcon(
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
@Preview
private fun BackArrowIconFilledPreview() {
    MaterialTheme {
        BackArrowIconFilled(
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}