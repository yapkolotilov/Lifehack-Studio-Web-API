package me.kolotilov.lifehackstudiowebapi.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_overview.*
import me.kolotilov.lifehackstudiowebapi.R
import me.kolotilov.lifehackstudiowebapi.common.FragmentWithViewModel

class OverviewFragment : FragmentWithViewModel<OverviewViewModel, OverviewViewModel.Factory>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.overviewData
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                overview_recycler.adapter = OverviewAdapter(data, this)
            }
            .autoDispose()
    }
}
