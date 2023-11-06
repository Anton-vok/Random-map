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
class Cor(nMaxX:Int,
          nMaxY: Int,
          nNowX: Int,
          nNowY: Int,
          nUpKf: Int,
          nDownKf: Int,
          nLeftKf: Int,
          nRightKf: Int){
    var map = Array<Array<Int>>(nMaxX,{Array<Int>(nMaxY,{0})})
    var maxX = nMaxX
    var maxY = nMaxY
    var nowX = nNowX
    var nowY = nNowY
    var upKf = nUpKf
    var downKf = nDownKf
    var leftKf = nLeftKf
    var rightKf = nRightKf
}
class RandomMapRoud(
    nMaxX:Int,
    nMaxY: Int,
    nNowX: Int,
    nNowY: Int,
    nUpKf: Int,
    nDownKf: Int,
    nLeftKf: Int,
    nRightKf: Int,
    nColorVoid: Color,
    nColorRoad: Color,
    nIter: Int,
    nSead: Int){
    var cor=Cor(nMaxX, nMaxY, nNowX, nNowY, nUpKf, nDownKf, nLeftKf, nRightKf)
    var colorVoid=nColorVoid
    var colorRoud=nColorRoad
    var iter=nIter
    //random
    private var inSeed=nSead
    private val multiplier: Long = 1103515245
    private val increment: Long = 12345
    private val modulus: Long = 2_147_483_647
    private fun nextInt(): Int {
        seed = (multiplier * seed + increment) % modulus
        return seed.toInt()
    }
    private var seed:Long=inSeed.toLong()
    private fun random(min:Int,max:Int):Int{
        val randomValue = (min+(nextInt()%(max-min+1))).coerceIn(min,max)
        return randomValue
    }
    //n random

    fun update(cor: Cor){
        var randomInt= Random.nextInt(0, 100)
        if (0<randomInt && 25>randomInt){ if (cor.nowX != 0) cor.nowX -= 1 else cor.nowX += 1}
        else if (25<randomInt && 50>randomInt){if (cor.nowX != cor.maxX - 1) cor.nowX += 1 else cor.nowX -= 1}
        else if (50<randomInt && 75>randomInt){if (cor.nowY != 0) cor.nowY -= 1 else cor.nowY += 1}
        else if (75<randomInt && 100>randomInt){if (cor.nowY != cor.maxY - 1) cor.nowY += 1 else cor.nowY -= 1}
        cor.map[cor.nowX][cor.nowY]=1
    }
    fun gen(){
        for (i in 0..iter){
            update(cor)
        }
    }
    fun genImg(){
        createImageFromMatrix(cor.map, colorVoid, colorRoud)
    }
}
