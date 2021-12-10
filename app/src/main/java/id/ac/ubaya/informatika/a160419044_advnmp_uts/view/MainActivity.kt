package id.ac.ubaya.informatika.a160419044_advnmp_uts.view

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import id.ac.ubaya.informatika.a160419044_advnmp_uts.R
import id.ac.ubaya.informatika.a160419044_advnmp_uts.util.createNotificationChannel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {
    private var sensorManager: SensorManager ?= null
    private lateinit var navController: NavController

    init {
        instance = this
    }

    companion object {
        private var instance:MainActivity ?= null

        fun showNotification(title:String, content:String, icon:Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            val builder = NotificationCompat.Builder(instance!!.applicationContext, channelId)
                .apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)
                }
            val notif = NotificationManagerCompat.from(instance!!.applicationContext)
            notif.notify(1001, builder.build())
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        navController = Navigation.findNavController(this, R.id.hostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)

        bottomNav.setupWithNavController(navController)

        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, true,
            getString(R.string.app_name), "App channel")

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout) || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()

        val proximitySensor = sensorManager?.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        if(proximitySensor != null) {
            sensorManager?.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_FASTEST)
        } else {
            Toast.makeText(this, "Not detected", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_PROXIMITY) {
            if(event.values[0] <= 0.0) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}