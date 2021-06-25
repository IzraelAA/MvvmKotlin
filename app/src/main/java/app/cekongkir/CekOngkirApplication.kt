package app.cekongkir

import android.app.Application
import app.cekongkir.database.preferences.CekOngkirPreference
import app.cekongkir.network.ApiService
import app.cekongkir.network.Endpoint
import app.cekongkir.network.repository.RajaOngkirRepository
import app.cekongkir.ui.city.CityViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import timber.log.Timber

class CekOngkirApplication : Application(),KodeinAware{

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(
                this@CekOngkirApplication
        ))
        bind() from  singleton {
            CekOngkirPreference(instance())
        }
        bind<Endpoint>() with  singleton{ApiService.getClient()}
        bind() from  singleton {
            RajaOngkirRepository(instance(),instance())
        }
        bind() from  singleton {
            CityViewModelFactory(instance())
        }
    }
}