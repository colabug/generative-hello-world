import org.openrndr.Program
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.loadFont
import org.openrndr.extras.color.presets.*
import kotlin.math.*

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        // Font
        val font = loadFont("data/fonts/default.otf", 64.0)

        extend {
            // Color Palette
            val colors = arrayOf(
                ColorRGBa.PEACH_PUFF,
                ColorRGBa.DARK_SEA_GREEN,
                ColorRGBa.BLUE_STEEL,
                ColorRGBa.LIGHT_CORAL,
                ColorRGBa.ANTIQUE_WHITE,
                ColorRGBa.MEDIUM_PURPLE
            )

            for (index in 1..32) {
                // Circle color & no border stroke
                drawer.fill = colors[index % colors.size]
                drawer.strokeWeight = 0.0

                // Construct animated circles
                circleVariationOne(index)
                circleVariationTwo(index)
                circleVariationThree(index)
                circleVariationFour(index)
            }

            // Text Configuration
            drawer.fontMap = font
            val textPosition = 200
            val shadowOffset = 2

            // Text Shadow
            drawer.fill = ColorRGBa.BLACK
            drawer.text("Hello Corey!", textPosition + shadowOffset + sin(seconds) * width / 4,  height / 2.0)

            // Text
            drawer.fill = ColorRGBa.WHITE
            drawer.text("Hello Corey!", textPosition + sin(seconds) * width / 4, height / 2.0)
        }
    }
}

private fun Program.circleVariationOne(value: Int) {
    drawer.circle(
        cos(seconds) * width / 2.0 + width / 2.0,
        sin(0.5 * seconds) * height / 2.0 + height / 2.0,
        1000.0 / value
    )
}

private fun Program.circleVariationTwo(value: Int) {
    drawer.circle(
        sin(seconds) * width / 2.0 + width / 2.0,
        cos(0.5 * seconds) * height / 2.0 + height / 2.0,
        1000.0 / value
    )
}

private fun Program.circleVariationThree(value: Int) {
    drawer.circle(
        sin(seconds) * width + width,
        cos(seconds) * height + height,
        1000.0 / value
    )
}

private fun Program.circleVariationFour(value: Int) {
    drawer.circle(
        cos(seconds) * width + width / 2.0,
        sin(0.5 * seconds) * height + height,
        1000.0 / value
    )
}

