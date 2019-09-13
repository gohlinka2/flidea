package cz.hlinkapp.flidea.di.modules

import android.app.Application
import cz.hlinkapp.flidea.di.FlideaApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * A Dagger2 module which provides the Application class.
 */
@Module
class ApplicationModule (private val application: FlideaApplication){

    @Singleton
    @Provides
    fun provideApplication() : Application = application
}