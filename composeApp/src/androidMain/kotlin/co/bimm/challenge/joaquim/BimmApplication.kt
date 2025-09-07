package co.bimm.challenge.joaquim

import android.app.Application
import co.bimm.challenge.di.initKoin
import org.koin.android.ext.koin.androidContext

class BimmApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BimmApplication)
        }
    }
}