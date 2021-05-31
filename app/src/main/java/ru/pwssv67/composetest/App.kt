package ru.pwssv67.composetest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import ru.pwssv67.composetest.ui.viewmodels.HealthCounterViewModel

class App:Application(), KoinComponent {
    private val viewModelModule = module {
        viewModel { HealthCounterViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger()
            androidContext(this@App)
            modules(viewModelModule)
        }
    }
}