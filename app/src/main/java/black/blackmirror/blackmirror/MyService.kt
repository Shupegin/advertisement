package black.blackmirror.blackmirror

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MyService : Service() {
    private lateinit var thread: Thread

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        Thread(Runnable {
            while (true) {


                val timeFormat: DateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                val timeText: String = timeFormat.format(Calendar.getInstance().time)
//
//                Handler(Looper.getMainLooper()).post(Runnable {
//                    binding.time.text = timeText
//                })

                Thread.sleep(1000)
            }
        })
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        thread.start()
    }
}