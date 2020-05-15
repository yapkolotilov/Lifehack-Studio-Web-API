package me.kolotilov.lifehackstudiowebapi

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import me.kolotilov.lifehackstudiowebapi.details.DetailsFragment

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, DetailsFragment.newInstance(1))
            .commit()
    }
}
