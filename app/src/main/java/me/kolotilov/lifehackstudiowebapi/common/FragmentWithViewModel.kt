package me.kolotilov.lifehackstudiowebapi.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class FragmentWithViewModel<VM : ViewModel, VMF : ViewModelProvider.Factory> : DaggerFragment() {

    @Inject
    internal lateinit var modelFactory: VMF

    protected lateinit var viewModel: VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, modelFactory).get(ViewModel::class.java) as VM
    }
}