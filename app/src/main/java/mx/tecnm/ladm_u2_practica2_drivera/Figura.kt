package mx.tecnm.ladm_u2_practica2_drivera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import java.util.*

class Figura (punteroLienzo: Lienzo, posX:Float, posY:Float, nombreImagen:Int) {

    var puntero = punteroLienzo
    var x = posX
    var y = posY
    var imagen = BitmapFactory.decodeResource(punteroLienzo.resources, nombreImagen)



    fun pintar(c: Canvas, p:Paint){
        c.drawBitmap(imagen, x, y, p)
    }//pintar


    fun coordRandom(){

        var r = Random()
                            //fin - inicio
        this.x = r.nextInt(700-50).toFloat()
        this.y = r.nextInt(1200-100).toFloat()

    }//coordRandom


    fun estaEnArea(toqueX:Float, toqueY:Float) : Boolean{

        var x2 = x+imagen.width
        var y2 = y+imagen.height

        if (toqueX >= x && toqueX <= x2){
            if (toqueY >= y && toqueY <= y2){
                return true
            }//if-y
        }//if-x

        return false
    }//estaEnArea


    fun manchar(c: Canvas, p:Paint, x:Float, y:Float){
        imagen = BitmapFactory.decodeResource(puntero.resources, R.drawable.splash)
        this.x = x
        this.y = y
        c.drawBitmap(imagen, x, y,p)
    }//manchar


}//class