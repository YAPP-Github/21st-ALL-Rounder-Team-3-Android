import android.app.Application
import com.yapp.all_rounder_3.BuildConfig
import timber.log.Timber

class AllRounder3Application : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}