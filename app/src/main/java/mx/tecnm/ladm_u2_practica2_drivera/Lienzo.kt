package mx.tecnm.ladm_u2_practica2_drivera

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import java.util.*

class Lienzo (p:MainActivity) : View(p) {

    var seg = 0
    var contMoscas = 0
    var r = Random()
    var numMocas = r.nextInt(100-80)+80

    var moscaX = 0
    var moscaY = 0

    var timerIniciado = false

    val timer = object : CountDownTimer(60000,1000){
        override fun onTick(millisUntilFinished: Long) {
            seg = seg +1
            redibujar()
            invalidate()
        }

        override fun onFinish() {

        }//onFinish

    }//timer


    var mosca = Figura(-1000, -1000, BitmapFactory.decodeResource(resources, R.drawable.mosca))


    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val paint = Paint()

        //fondo
        c.drawColor(Color.DKGRAY)


        //contador
        paint.textSize=50f
        paint.setColor(Color.BLUE)
        c.drawText(seg.toString(),650f,50f,paint)
        c.drawText(numMocas.toString(),650f,100f,paint)
        c.drawText(contMoscas.toString(),650f,150f,paint)


        //mosca
        mosca.pintar(c, paint)

    }//onDraw


    override fun onTouchEvent(event: MotionEvent): Boolean {

        if(event.action == MotionEvent.ACTION_DOWN){

            if (timerIniciado == false){
                timer.start()
                timerIniciado = true
            }

        }//ActionDown

        return true
    }//onTouchEvent


    fun redibujar(){

        mosca.coordRandom()
        invalidate()

    }//redibujar



}//class




////
//alto 1256
//ancho 720
//--Para saber medidas
//paint.color = Color.BLACK
//c.drawText("alto: "+height.toInt()+"ancho: "+width.toInt(),50f,50f,paint)