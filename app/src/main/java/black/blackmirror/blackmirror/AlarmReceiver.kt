package black.blackmirror.blackmirror

import android.R
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt
import android.app.NotificationManager
import android.graphics.BitmapFactory


class AlarmReceiver : BroadcastReceiver() {


    @SuppressLint("WrongConstant")
    override fun onReceive(context: Context, intent: Intent?) {
        startImage(context)
        start(context)
    }

    private fun startImage(context: Context) {
        val myArray = arrayOf(1, 2, 3, 4)

        val rand = Random.nextInt(myArray.size)
        val name = "${myArray[rand]}"

        sendNotification2(context)

    }

    companion object  {
        private  val CHANNEl_ID = "channel_id_example_01"
        private val notificationID = 101


        var i = 1


        fun start(context: Context) {
//            val interval = 60000L
             time(context)

            Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
        }


        fun stop(context: Context) {
//            manager.cancel(pendingIntent)
//            Toast.makeText(context, "Stop", Toast.LENGTH_SHORT).show()

        }

        private fun time(context: Context) {
            when(i){
                1 -> one(context)
                2 -> two(context)
                3 -> three(context)
                4 -> four(context)
            }

        }

        fun one(context: Context){
            val time1 = Calendar.getInstance()
            time1[Calendar.HOUR_OF_DAY] = 12
            time1[Calendar.MINUTE] = 5
            time1[Calendar.SECOND] = 0
            var manager = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context, AlarmReceiver::class.java)
            var  pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0)

            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time1.timeInMillis, pendingIntent);


            i = 2

        }
        fun two(context: Context){
            val time2 = Calendar.getInstance()
            time2[Calendar.HOUR_OF_DAY] = 14
            time2[Calendar.MINUTE] = 55
            time2[Calendar.SECOND] = 0

            var manager = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context, AlarmReceiver::class.java)
            var pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0)

            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time2.timeInMillis, pendingIntent);
            i = 3

        }
        fun three(context: Context){
            val time3 = Calendar.getInstance()
            time3[Calendar.HOUR_OF_DAY] = 15
            time3[Calendar.MINUTE] = 0
            time3[Calendar.SECOND] = 0

            var manager = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context, AlarmReceiver::class.java)
            var pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0)

            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time3.timeInMillis, pendingIntent);
            i = 4

        }
        fun four (context: Context){
            val time4 = Calendar.getInstance()
            time4[Calendar.HOUR_OF_DAY] = 15
            time4[Calendar.MINUTE] = 5
            time4[Calendar.SECOND] = 0

            var manager = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context, AlarmReceiver::class.java)
           var pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0)

            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time4.timeInMillis, pendingIntent);
            manager.cancel(pendingIntent)

        }

        fun sendNotification2 (context: Context){
            var intent = Intent(context,
                AdvertisementActivity::class.java)
            var pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT)

            val builder = NotificationCompat.Builder(context,CHANNEl_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.bottom_bar)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources,R.drawable.alert_dark_frame))
                .setTicker("НОВОЕ УВЕДОМЛЕНИЕ")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Super")
                .setContentText("Жми Бегом")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            with(NotificationManagerCompat.from(context)){
                notify(notificationID,builder.build())
            }

        }


    }


}