package mx.tecnm.ladm_u2_practica2_drivera

import android.app.AlertDialog
import android.graphics.*
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import java.util.*

class Lienzo (p:MainActivity) : View(p) {

    var seg = 60
    var contMoscas = 0
    var r = Random()
    var numMocas = r.nextInt(100-80)+80
    var aplastada = false
    var timerIniciado = false
    var banderaFinal = true
    var juego = true
    var xSplash = -1000f
    var ySplash = -1000f

    val timer = object : CountDownTimer(60000,1000){

        override fun onTick(millisUntilFinished: Long) {

            seg --
            redibujar()
            invalidate()

        }//onTick

        override fun onFinish() {

            banderaFinal = true
            juego = false
            //invalidate()

            if(contMoscas>=80){

                AlertDialog.Builder(p)
                        .setTitle("Felicidades")
                        .setMessage("Moscas aplastadas: ${contMoscas} de ${numMocas}")
                        .setPositiveButton("Ok"){d,i->d.dismiss()}
                        .show()

            }else{

                AlertDialog.Builder(p)
                        .setTitle("Resultado")
                        .setMessage("Moscas aplastadas: ${contMoscas}")
                        .setPositiveButton("Ok"){d,i->d.dismiss()}
                        .show()

            }//else


        }//onFinish

    }//timer

    var mosca0 = Figura(this, -1000f, -1000f, R.drawable.mosca)
    var mosca1 = Figura(this, -1000f, -1000f, R.drawable.mosca)
    var splash = Figura(this, xSplash, ySplash, R.drawable.splash)


    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val paint = Paint()

        //fondo
        c.drawColor(Color.DKGRAY)

        if (juego){

            //contador
            paint.textSize=40f
            paint.setColor(Color.BLUE)
            c.drawText("Tiempo restante ${seg}",350f,50f,paint)
            c.drawText(numMocas.toString(),650f,100f,paint)
            c.drawText(contMoscas.toString(),650f,150f,paint)


            //mosca
            mosca0.pintar(c, paint)
            mosca1.pintar(c, paint)


            //splash
            if(aplastada){
                splash.pintar(c, paint)
            }//if-Aplastada

        }//if-Juego

    }//onDraw


    override fun onTouchEvent(event: MotionEvent): Boolean {

        aplastada = false

        if (juego){

            if(event.action == MotionEvent.ACTION_DOWN){

                if (timerIniciado == false){

                    timer.start()
                    timerIniciado = true

                }//if-timerIniciado

                if(mosca0.estaEnArea((event.x), event.y)){

                    contMoscas = contMoscas+1
                    numMocas--
                    aplastada = true

                    splash.manchar(Canvas(),Paint(),(event.x), event.y)
                    invalidate()

                }//if-mosca-Area

                if(mosca1.estaEnArea(event.x, event.y)){

                    contMoscas = contMoscas+1
                    numMocas--
                    aplastada = true

                    splash.manchar(Canvas(),Paint(),event.x, event.y)
                    invalidate()

                }//if-mosca-Area

            }//ActionDown

        }//if-juego

        return true
    }//onTouchEvent


    fun redibujar(){

        mosca0.coordRandom()
        mosca1.coordRandom()

    }//redibujar


}//class




////
//alto 1256
//ancho 720
//--Para saber medidas
//paint.color = Color.BLACK
//c.drawText("alto: "+height.toInt()+"ancho: "+width.toInt(),50f,50f,paint)