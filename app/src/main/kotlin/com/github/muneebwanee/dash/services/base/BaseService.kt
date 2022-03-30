package com.github.muneebwanee.dash.services.base

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.github.muneebwanee.dash.app.Dash
import com.github.muneebwanee.dash.di.component.DaggerServiceComponent
import com.github.muneebwanee.dash.di.component.ServiceComponent
import com.github.muneebwanee.dash.di.module.ServiceModule
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
/**
 * Created by muneebwanee on 15/12/20.
 */
abstract class BaseService : Service(), InterfaceService  {


    private lateinit var compositeDisposable: CompositeDisposable

    companion object {
        @JvmStatic
        lateinit var serviceComponent: ServiceComponent
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        serviceComponent = DaggerServiceComponent.builder()
                .serviceModule(ServiceModule(this))
                .appComponent(Dash.appComponent).build()
        compositeDisposable = CompositeDisposable()
    }

    override fun getComponent(): ServiceComponent? = serviceComponent


    override fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun clearDisposable() = compositeDisposable.clear()


}
