package me.kolotilov.lifehackstudiowebapi.common

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class FragmentWithViewModel<VM : ViewModel, VMF : ViewModelProvider.Factory> : DaggerFragment() {

    private var _onStopDisposable = CompositeDisposable()

    @Inject
    internal lateinit var modelFactory: VMF

    protected lateinit var viewModel: VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, modelFactory).get(ViewModel::class.java) as VM
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _onStopDisposable = CompositeDisposable()
    }

    override fun onStop() {
        super.onStop()
        _onStopDisposable.dispose()
    }

    protected fun Disposable.autoDispose() {
        _onStopDisposable.add(this)
    }
}