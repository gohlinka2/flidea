package cz.hlinkapp.flidea.di

import android.app.Application
import cz.hlinkapp.flidea.di.components.DaggerFlideaAppComponent
import cz.hlinkapp.flidea.di.components.FlideaAppComponent
import cz.hlinkapp.flidea.di.modules.ApplicationModule
import cz.hlinkapp.flidea.di.modules.RetrofitModule
import cz.hlinkapp.flidea.di.modules.RoomModule
import cz.hlinkapp.flidea.di.modules.UtilsModule

/**
 * A custom [Application] class for this app.
 * Needed mainly for DI.
 */
class FlideaApplication : Application() {

    private lateinit var flideaAppComponent : FlideaAppComponent

    override fun onCreate() {
        super.onCreate()

        // Init the app component
        flideaAppComponent = DaggerFlideaAppComponent
            .builder()
            .roomModule(RoomModule(this))
            .applicationModule(ApplicationModule(this))
            .utilsModule(UtilsModule(this))
            .retrofitModule(RetrofitModule())
            .build()
    }


    /**
     * Returns the Dagger2 main application component.
     */
    fun getApplicationComponent() : FlideaAppComponent = flideaAppComponent
}