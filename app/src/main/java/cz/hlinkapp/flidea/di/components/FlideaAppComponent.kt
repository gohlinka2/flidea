package cz.hlinkapp.flidea.di.components

import android.app.Application
import cz.hlinkapp.flidea.activities.MainActivity
import cz.hlinkapp.flidea.di.FlideaApplication
import cz.hlinkapp.flidea.di.modules.*
import cz.hlinkapp.flidea.fragments.FlideaFragment
import dagger.Component
import javax.inject.Singleton


/**
 * Main app component for Dagger2.
 */
@Singleton
@Component(modules = [ApplicationModule::class,RetrofitModule::class,RoomModule::class,UtilsModule::class,ViewModelModule::class])
interface FlideaAppComponent {

    fun inject(application: FlideaApplication)

    fun inject(activity: MainActivity)

    fun inject(fragment: FlideaFragment)

    fun application() : Application
}
