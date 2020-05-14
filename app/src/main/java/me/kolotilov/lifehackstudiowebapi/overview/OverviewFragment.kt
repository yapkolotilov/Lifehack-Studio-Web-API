package me.kolotilov.lifehackstudiowebapi.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_overview.*
import me.kolotilov.lifehackstudiowebapi.R
import me.kolotilov.lifehackstudiowebapi.utils.autoDispose
import timber.log.Timber
import javax.inject.Inject

class OverviewFragment : DaggerFragment() {

    @Inject lateinit var factory: OverviewViewModel.Factory

    lateinit var viewModel: OverviewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, factory).get(OverviewViewModel::class.java)

        viewModel.overviewData
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                overview_recycler.adapter = OverviewAdapter(data)
            }
            .autoDispose()
    }

    companion object {

        fun newInstance() = OverviewFragment()
    }
}
