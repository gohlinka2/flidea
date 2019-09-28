package cz.hlinkapp.flidea.di.components

import android.app.Application
import cz.hlinkapp.flidea.activities.MainActivity
import cz.hlinkapp.flidea.di.FlideaApplication
import cz.hlinkapp.flidea.di.modules.*
import cz.hlinkapp.flidea.fragments.FlideaFragment
import cz.hlinkapp.flidea.fragments.SearchFilterFragment
import dagger.Component
import javax.inject.Singleton


/**
 * Main app component for Dagger2.
 */
@Singleton
@Component(modules = [ApplicationModule::class,RetrofitModule::class,RoomModule::class,UtilsModule::class,ViewModelModule::class])
interface FlideaAppComponent {

    /**
     * Inject to [FlideaApplication].
     */
    fun inject(application: FlideaApplication)

    /**
     * Inject to [MainActivity].
     */
    fun inject(activity: MainActivity)

    /**
     * Inject to [FlideaFragment].
     */
    fun inject(fragment: FlideaFragment)

    /**
     * Inject to [SearchFilterFragment].
     */
    fun inject(fragment: SearchFilterFragment)

    /**
     * Returns this application's class.
     */
    fun application() : Application
}
