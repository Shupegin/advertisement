package black.blackmirror.blackmirror

import android.R

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import black.blackmirror.blackmirror.databinding.ActivityMainBinding

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    companion object {
        public var self: MainActivity? = null
    }

    var stopWhile: Boolean = false
    lateinit var binding: ActivityMainBinding
    val currentDate = Date()

    private lateinit var thread: Thread

    private val CHANNEl_ID = "channel_id_example_01"
    private val notificationID = 101

    private var pendingIntent: PendingIntent? = null

    lateinit var nm: NotificationManager


    @SuppressLint("BatteryLife")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun ignoreBatteryOptimization() {
        val intent = Intent()
        val packN = packageName
        val pm = getSystemService(Context.POWER_SERVICE) as PowerManager
        if (!pm.isIgnoringBatteryOptimizations(packN)) {
            intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
            intent.data = Uri.parse("package:$packN")
            startActivity(intent)
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ignoreBatteryOptimization()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChanal()
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val dateText: String = dateFormat.format(currentDate)

        nm = applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager


        binding.data.text = dateText


        binding.startBtn.setOnClickListener {
            AlarmReceiver.start(applicationContext)
            Toast.makeText(this,"Start",Toast.LENGTH_LONG).show()
        }


        self = this

        dowork()
    }


//    @SuppressLint("ResourceAsColor")
//    fun imageView(int: Int) {
//
//        when (int) {
//            1 -> binding.imageView.setBackgroundResource(R.color.holo_blue_dark)
//            2 -> binding.imageView.setBackgroundResource(R.color.darker_gray)
//            3 -> binding.imageView.setBackgroundResource(R.color.holo_green_dark)
//            4 -> binding.imageView.setBackgroundResource(R.color.holo_orange_dark)
//        }
//
//
//    }


    override fun onDestroy() {
        self = null
        stopWhile = false

        super.onDestroy()
    }


    private fun createNotificationChanal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "title"
            val descriptionText = "Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEl_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }

    }


    private fun dowork() {
        Thread(Runnable {
            stopWhile = true
            while (stopWhile) {
                val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                val timeText: String = timeFormat.format(Calendar.getInstance().time)
                Handler(Looper.getMainLooper()).post(Runnable {
                    binding.time.text = timeText
                })
                Thread.sleep(1000)
            }
        }).start()

    }


    fun sendNotification() {


        var intent = Intent(applicationContext, AdvertisementActivity1::class.java)
        var pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        val builder = NotificationCompat.Builder(this, CHANNEl_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.bottom_bar)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    applicationContext.resources,
                    R.drawable.alert_dark_frame
                )
            )
            .setTicker("НОВОЕ УВЕДОМЛЕНИЕ")
            .setWhen(System.currentTimeMillis())
            .setAutoCancel(true)
            .setContentTitle("Super")
            .setContentText("Жми Бегом")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)) {
            notify(notificationID, builder.build())
        }

    }
}