import org.openrndr.Program
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.loadFont
import org.openrndr.extras.color.presets.GREY
import org.openrndr.shape.Circle
import org.openrndr.shape.ShapeContour

private const val FONT_WEIGHT = 64.0

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        val font = loadFont("data/fonts/default.otf", FONT_WEIGHT)

        extend {
            val albumX = height / 2.0 + 50
            val albumY = height / 2.0
            val albumRadius = width / 4.0 - 20

            // Create album first so that it's shown behind the cover
            createAlbum(albumX, albumY, albumRadius)
            createAlbumGrooves(albumX, albumY, albumRadius)

            // Create album cover
            // TODO: Add cover art
            createAlbumCover()
            createAlbumTitle(font)

            // TODO: Move album out of cover
        }
    }
}

private fun Program.createAlbum(albumX: Double, albumY: Double, albumRadius: Double) {
    // No outer stroke
    drawer.strokeWeight = 0.0

    // Main album
    drawer.fill = ColorRGBa.GREY
    drawer.circle(albumX, albumY, albumRadius)

    // Label
    drawer.fill = ColorRGBa.WHITE
    drawer.circle(albumX, albumY, albumRadius / 3 - 10)

    // Center hole
    drawer.fill = ColorRGBa.BLACK
    drawer.circle(albumX, albumY, albumRadius / 20)
}

private fun Program.createAlbumGrooves(
    albumX: Double,
    albumY: Double,
    albumRadius: Double
) {
    drawer.stroke = ColorRGBa.WHITE
    drawer.strokeWeight = 4.0

    drawer.contour(createGroove(albumX, albumY, albumRadius, 1, -25))
    drawer.contour(createGroove(albumX, albumY, albumRadius, 2, 25))
    drawer.contour(createGroove(albumX, albumY,albumRadius, 3, 15 ))
}

private fun Program.createGroove(
    albumX: Double,
    albumY: Double,
    albumRadius: Double,
    grooveDivisor: Int,
    grooveOffset: Int
): ShapeContour {
    return Circle(albumX, albumY, albumRadius / grooveDivisor + grooveOffset)
                  .contour.sub(seconds * 0.1, seconds * 0.1 + 0.1)
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

