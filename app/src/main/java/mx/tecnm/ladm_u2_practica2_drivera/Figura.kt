package mx.tecnm.ladm_u2_practica2_drivera

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import java.util.*

class Figura () {

    var x = 0f
    var y = 0f
    var ancho = 0f
    var alto = 0f
    var bitmap : Bitmap ?= null


    //imagen
    constructor(x:Int, y:Int, imagen:Bitmap) : this(){

        bitmap = imagen
        this.x = x.toFloat()
        this.y = y.toFloat()
        ancho = bitmap!!.width.toFloat()
        alto = bitmap!!.height.toFloat()

    }//constructor


    fun pintar(c: Canvas, p:Paint){
        c.drawBitmap(bitmap!!, x, y, p)
    }//pintar


    fun coordRandom(){

        var r = Random()
                            //fin - inicio
        this.x = r.nextInt(700-50).toFloat()
        this.y = r.nextInt(1200-100).toFloat()

    }//coordRandom

}//class