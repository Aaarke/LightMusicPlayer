package com.example.aaarke.lightmusicplayer

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception

class MainActivity : AppCompatActivity(),SensorEventListener {
    var sensor:Sensor?=null
    var sensorManager:SensorManager?=null
    var isRunning: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSensor()

    }

    private fun initSensor() {
        sensorManager=getSystemService(Context.SENSOR_SERVICE)as SensorManager
        sensor=sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this)
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event!!.values[0]>40&&isRunning==false){
            //Play music player
            isRunning=true
            try {
                var mp:MediaPlayer=MediaPlayer()
                mp.setDataSource("http://2017.downloadming11.me/bollywood%20mp3/Simmba%20(2018)/Simmba%20(2018)%20(320%20Kbps)/01%20-%20Aankh%20Marey%20-%20DownloadMing.Se.mp3")
                mp.prepare()
                mp.start()
            }catch (e:Exception){

            }
        }
    }
}
