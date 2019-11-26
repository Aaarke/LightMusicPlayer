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
import java.util.*
import android.media.RingtoneManager
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity(),SensorEventListener {
    var sensor:Sensor?=null
    var sensorManager:SensorManager?=null
    var isRunning: Boolean? = false

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
        var mp:MediaPlayer=MediaPlayer()
        if(event!!.values[0]>0&&isRunning==false){
            //Play music player
            isRunning=true
            try {
                val alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                mp.setDataSource(this,alert)
                mp.prepare()
                mp.start()
            }catch (e:Exception){
                isRunning=false

            }
        }else{
            if (isRunning!!){
                mp.stop()
            }

        }
    }
}
