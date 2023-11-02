import java.awt.Color
import kotlin.random.Random
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun createImageFromMatrix(matrix: Array<Array<Int>>, oneColor:Color, zeroColor: Color) {
    val numRows = matrix.size
    val numCols = matrix[0].size

    val image = BufferedImage(numCols, numRows, BufferedImage.TYPE_INT_RGB)

    for (row in 0 until numRows) {
        for (col in 0 until numCols) {
            val pixelValue = if (matrix[row][col] == 1) oneColor.rgb else zeroColor.rgb  // Black for 1, White for 0
            image.setRGB(col, row, pixelValue)
        }
    }

    try {
        ImageIO.write(image, "png", File("output.png"))
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

class randomMap(nMaxX: Int, nMaxY: Int, nOneColor: Color, nZeroColor: Color, nOneKf: Int, nZeroKf: Int){
    var oneColor=nOneColor
    var zeroColor=nZeroColor
    var maxX=nMaxX
    var maxY=nMaxY
    var map=Array<Array<Int>>(maxX, { Array<Int>(maxY, {0}) })
    var oneKf=nOneKf
    var zeroKf=nZeroKf

    fun gen(){
        for (i in 0..maxX-1){
            for (j in 0..maxY-1){
                var random=Random.nextInt(0,101)
                if (0 < random && random < oneKf){
                    map[i][j]=1
                } else if (oneKf < random && random < zeroKf){
                    map[i][j]=0
                }
            }
        }
    }
    fun genImg(){
        createImageFromMatrix(map, oneColor, zeroColor)
    }
}
