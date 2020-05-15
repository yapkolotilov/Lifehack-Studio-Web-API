package me.kolotilov.lifehackstudiowebapi.uri

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.uri_button.view.*
import me.kolotilov.lifehackstudiowebapi.R
import me.kolotilov.lifehackstudiowebapi.utils.notNull
import me.kolotilov.lifehackstudiowebapi.utils.safeStartActivity

class UriView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)

    constructor(context: Context, attributes: AttributeSet, style: Int) : super(context, attributes, style)


    private fun init(data: Uri, text: String, viewDataFactory: ViewDataFactory) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.uri_button, this, true)

        uri_description.text = viewDataFactory.description
        uri_data.text = text
        uri_image.setImageDrawable(viewDataFactory.icon)
        setOnClickListener { viewDataFactory.onClickListener(data) }
    }

    companion object {

        fun newPhoneUriView(context: Context, text: String, data: Uri) = UriView(context).apply {
            init(data, text, ViewDataFactory.Phone(context))
        }

        fun newMapUriView(context: Context, text: String, data: Uri) = UriView(context).apply {
            init(data, text, ViewDataFactory.Map(context))
        }

        fun newWebUriView(context: Context, text: String, data: Uri) = UriView(context).apply {
            init(data, text, ViewDataFactory.Web(context))
        }
    }

    private sealed class ViewDataFactory {
        abstract val description: String
        abstract val icon: Drawable
        abstract val onClickListener: (uri: Uri) -> Unit

        class Phone(context: Context) : ViewDataFactory() {
            override val description = "Phone"

            override val icon: Drawable = ContextCompat.getDrawable(context, R.drawable.ic_call_icon).notNull()

            override val onClickListener: (uri: Uri) -> Unit = {
                val intent = Intent(Intent.ACTION_DIAL, it)
                safeStartActivity(context, intent)
            }
        }

        class Map(context: Context) : ViewDataFactory() {
            override val description = "Coordinates"

            override val icon = ContextCompat.getDrawable(context, R.drawable.ic_map_icon).notNull()

            override val onClickListener: (uri: Uri) -> Unit = {
                val intent = Intent(Intent.ACTION_VIEW, it)
                safeStartActivity(context, intent)
            }
        }

        class Web(context: Context) : ViewDataFactory() {
            override val description = "Web page"

            override val icon = ContextCompat.getDrawable(context, R.drawable.ic_web_icon).notNull()

            override val onClickListener: (uri: Uri) -> Unit = {
                val intent = Intent(Intent.ACTION_VIEW, it)
                safeStartActivity(context, intent)
            }
        }
    }
}
