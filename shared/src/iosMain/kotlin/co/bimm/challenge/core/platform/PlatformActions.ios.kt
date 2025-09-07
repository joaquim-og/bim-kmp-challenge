package co.bimm.challenge.core.platform

import kotlinx.cinterop.BetaInteropApi
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.Foundation.NSString
import platform.Foundation.stringByAddingPercentEncodingWithAllowedCharacters
import platform.Foundation.NSCharacterSet
import platform.Foundation.URLQueryAllowedCharacterSet
import platform.Foundation.create

actual class PlatformActions {

    @OptIn(BetaInteropApi::class)
    actual fun openMap(address: String) {
        val encodedAddress = NSString.create(string = address)
            .stringByAddingPercentEncodingWithAllowedCharacters(
                NSCharacterSet.URLQueryAllowedCharacterSet()
            ) ?: address

        val appleMapsUrl = "http://maps.apple.com/?q=$encodedAddress"
        val appleUrl = NSURL.URLWithString(appleMapsUrl)

        if (appleUrl != null && UIApplication.sharedApplication.canOpenURL(appleUrl)) {
            UIApplication.sharedApplication.openURL(appleUrl)
        } else {
            val googleMapsUrl = "https://www.google.com/maps/search/$encodedAddress"
            val googleUrl = NSURL.URLWithString(googleMapsUrl)
            if (googleUrl != null) {
                UIApplication.sharedApplication.openURL(googleUrl)
            }
        }
    }

    actual fun openWebsite(url: String) {
        val nsUrl = NSURL.URLWithString(url)
        if (nsUrl != null && UIApplication.sharedApplication.canOpenURL(nsUrl)) {
            UIApplication.sharedApplication.openURL(nsUrl)
        }
    }
}