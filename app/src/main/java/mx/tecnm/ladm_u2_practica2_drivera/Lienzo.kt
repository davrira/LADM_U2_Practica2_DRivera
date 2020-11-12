package mx.tecnm.ladm_u2_practica2_drivera

import android.graphics.*
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import java.util.*

class Lienzo (p:MainActivity) : View(p) {

    var seg = 0
    var contMoscas = 0
    var r = Random()
    var numMocas = r.nextInt(100-80)+80
    var aplastada = false
    var timerIniciado = false
    var coordenadas = ""

    var xSplash = -1000f
    var ySplash = -1000f

    val timer = object : CountDownTimer(60000,1000){

        override fun onTick(millisUntilFinished: Long) {

            seg = seg +1
            redibujar()
            invalidate()

        }//onTick

        override fun onFinish() {

        }//onFinish

    }//timer

    var mosca = Figura(this, -1000f, -1000f, R.drawable.mosca)
    var splash = Figura(this, xSplash, ySplash, R.drawable.splash)


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
        c.drawText(coordenadas,100f,200f, paint)


        //mosca
        mosca.pintar(c, paint)

        //splash
        if(aplastada){
            splash.pintar(c, paint)
        }//

    }//onDraw


    override fun onTouchEvent(event: MotionEvent): Boolean {

        aplastada = false

        if(event.action == MotionEvent.ACTION_DOWN){

            if (timerIniciado == false){
                timer.start()
                timerIniciado = true
            }//

            if(mosca.estaEnArea(event.x, event.y)){
                contMoscas = contMoscas+1
                aplastada = true

                splash.manchar(Canvas(),Paint(),event.x, event.y)
                invalidate()
            }//

        }//ActionDown
        
        return true
    }//onTouchEvent


    fun redibujar(){

        mosca.coordRandom()

    }//redibujar


}//class




////
//alto 1256
//ancho 720
//--Para saber medidas
//paint.color = Color.BLACK
//c.drawText("alto: "+height.toInt()+"ancho: "+width.toInt(),50f,50f,paint)