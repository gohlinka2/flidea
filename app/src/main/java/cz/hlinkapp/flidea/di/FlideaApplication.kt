package cz.hlinkapp.flidea.di

import android.app.Application
import cz.hlinkapp.flidea.di.components.FlideaAppComponent

class FlideaApplication : Application() {

    private lateinit var flideaAppComponent : FlideaAppComponent

    override fun onCreate() {
        super.onCreate()


    }


    /**
     * Returns the Dagger2 main application component.
     */
    fun getApplicationComponent() : FlideaAppComponent = flideaAppComponent
}