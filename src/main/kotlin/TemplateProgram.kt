import org.openrndr.Program
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.loadFont
import org.openrndr.extras.color.presets.GREY

private const val FONT_WEIGHT = 64.0

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        val font = loadFont("data/fonts/default.otf", FONT_WEIGHT)

        extend {
            // Create album first so that it's shown behind the cover
            createSpinningAlbum()

            // Create album cover
            // TODO: Add cover art
            createAlbumCover()
            createAlbumTitle(font)

            // TODO: Move album out of cover
        }
    }
}

private fun Program.createSpinningAlbum() {
    drawer.fill = ColorRGBa.GREY
    drawer.strokeWeight = 0.0
    drawer.circle(
        height / 2.0 + 50,
        height / 2.0,
        width / 4.0
    )
}

private fun Program.createAlbumCover() {
    drawer.strokeWeight = 1.0
    drawer.stroke = ColorRGBa.WHITE
    drawer.fill = ColorRGBa.BLACK
    drawer.rectangle(100.0, 100.0, width / 2.0, width / 2.0)
}

private fun Program.createAlbumTitle(font: FontImageMap) {
    drawer.fontMap = font
    drawer.fill = ColorRGBa.WHITE
    drawer.text("Dark Side", 125.0, 450.0)
}

