package me.kolotilov.lifehackstudiowebapi.details

import android.net.Uri.parse
import android.os.Bundle
import android.text.Html
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.coroutines.*
import me.kolotilov.lifehackstudiowebapi.R
import me.kolotilov.lifehackstudiowebapi.common.FragmentWithViewModel
import me.kolotilov.lifehackstudiowebapi.details.DetailsFragmentDirections.Companion.actionDetailsFragmentToOverviewFragment
import me.kolotilov.lifehackstudiowebapi.uri.UriView
import me.kolotilov.lifehackstudiowebapi.utils.BASE_URL
import timber.log.Timber

private const val ID_KEY = "ID"

class DetailsFragment : FragmentWithViewModel<DetailsViewModel, DetailsViewModel.Factory>() {

    private var job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        view.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                scope.cancel()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        viewModel.getDetails(DetailsFragmentArgs.fromBundle(arguments!!).id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                item_name.text = data.name
                Glide.with(this)
                    .load(BASE_URL + data.imgSrc)
                    .placeholder(R.drawable.image_placeholder)
                    .into(item_image)

                item_description.text = data.description

                if (data.phone.isNotEmpty())
                    uri_list.addView(UriView.newPhoneUriView(context!!, data.phone,
                        parse("tel:${data.phone}")
                    ))

                if (data.url.isNotEmpty())
                    uri_list.addView(UriView.newWebUriView(context!!, data.url,
                        parse("https://${data.url}")
                    ))

                if (data.latitude != 0f)
                    uri_list.addView(UriView.newMapUriView(context!!, "Open in maps",
                        parse("geo:${data.latitude},${data.longitude}")
                    ))
            }, {
                val errorText = Html.fromHtml("<font color=\"#ffffff\">Invalid web data</font>")
                Snackbar.make(view, errorText, Snackbar.LENGTH_SHORT).show()
                scope.launch { redirectBackIn(1000) }
            })
            .autoDispose()
    }

    private suspend fun redirectBackIn(delayTime: Long) {
        delay(delayTime)
        runCatching {
            findNavController().navigate(actionDetailsFragmentToOverviewFragment())
        }
    }

    companion object {

        fun newInstance(id: Long) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(ID_KEY, id)
            }
        }
    }
}
