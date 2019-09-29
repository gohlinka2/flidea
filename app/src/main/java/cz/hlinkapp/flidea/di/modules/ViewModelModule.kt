package cz.hlinkapp.flidea.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cz.hlinkapp.flidea.utils.FlideaViewModelFactory
import cz.hlinkapp.flidea.utils.ViewModelKey
import cz.hlinkapp.flidea.view_models.LocationSearchViewModel
import cz.hlinkapp.flidea.view_models.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * A Dagger2 module which provides ViewModel-related classes.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel (mainViewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocationSearchViewModel::class)
    abstract fun bindLocationSearchViewModel (locationSearchViewModel: LocationSearchViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: FlideaViewModelFactory): ViewModelProvider.Factory
}