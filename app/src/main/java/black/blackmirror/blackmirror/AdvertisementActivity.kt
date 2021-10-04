package black.blackmirror.blackmirror

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception
//import com.google.android.gms.ads.AdListener
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.InterstitialAd

class AdvertisementActivity : AppCompatActivity() {



//    private var mInterstittialAd: InterstitialAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.advertisement_activity)

//        mInterstittialAd = InterstitialAd(this)
//        mInterstittialAd!!.adUnitId = "взять с сайта адмод"
//        val adRequest = AdRequest.Builder().build()
//        mInterstittialAd!!.loadAd(adRequest)
//
//
//        mInterstittialAd!!.adListener = object : AdListener() {
//            override fun onAdClosed() {
//                try {
//                    val intent = Intent(this@AdvertisementActivity, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }
        loadAdvertisement()
    }

    private fun loadAdvertisement() {

//        if (mInterstittialAd!!.isLoaded) {
//            mInterstittialAd!!.show()
//        } else {
//            try {
//                val intent = Intent(this@AdvertisementActivity, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }

    }
}