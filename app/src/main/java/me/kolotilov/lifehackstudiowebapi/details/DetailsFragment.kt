package me.kolotilov.lifehackstudiowebapi.details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_details.*
import me.kolotilov.lifehackstudiowebapi.R
import me.kolotilov.lifehackstudiowebapi.common.FragmentWithViewModel
import me.kolotilov.lifehackstudiowebapi.uri.UriView
import me.kolotilov.lifehackstudiowebapi.utils.BASE_URL
import me.kolotilov.lifehackstudiowebapi.utils.autoDispose

private const val ID_KEY = "ID"

class DetailsFragment : FragmentWithViewModel<DetailsViewModel, DetailsViewModel.Factory>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDetails(DetailsFragmentArgs.fromBundle(arguments!!).id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                item_name.text = data.name
                Glide.with(this)
                    .load(BASE_URL + data.imgSrc)
                    .into(item_image)

                if (data.phone.isNotEmpty())
                    uri_list.addView(UriView.newPhoneUriView(context!!, data.phone,
                        Uri.parse("tel:${data.phone}")))

                if (data.url.isNotEmpty())
                    uri_list.addView(UriView.newWebUriView(context!!, data.url,
                        Uri.parse("https://${data.url}")))

                if (data.latitude != 0f)
                    uri_list.addView(UriView.newMapUriView(context!!, "Open in maps",
                        Uri.parse("geo:${data.latitude},${data.longitude}")))
            }
            .autoDispose()
    }

    companion object {

        fun newInstance(id: Long) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putLong(ID_KEY, id)
            }
        }
    }
}
