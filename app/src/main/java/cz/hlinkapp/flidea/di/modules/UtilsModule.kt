package cz.hlinkapp.flidea.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * A Dagger2 module which provides several utility classes.
 */
@Module
class UtilsModule(private val app: Application) {

    @Provides
    fun provideExecutor(): Executor {
        return Executors.newCachedThreadPool()
    }

    @Provides
    fun provideSharedPreferences() : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)

    @Provides
    @Singleton
    fun provideConnectivityManager() : ConnectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    @Singleton
    fun provideGson() : Gson = GsonBuilder().serializeNulls().create()

}