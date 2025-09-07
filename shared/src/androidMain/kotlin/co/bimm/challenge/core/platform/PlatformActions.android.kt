package co.bimm.challenge.core.platform

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

actual class PlatformActions(private val context: Context) {

    actual fun openMap(address: String) {
        try {
            val gmmIntentUri = "geo:0,0?q=${Uri.encode(address)}".toUri()
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")

            if (mapIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(mapIntent)
            } else {
                val genericMapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                context.startActivity(genericMapIntent)
            }
        } catch (e: Exception) {
            openWebsite("https://www.google.com/maps/search/${Uri.encode(address)}")
        }
    }

    actual fun openWebsite(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}