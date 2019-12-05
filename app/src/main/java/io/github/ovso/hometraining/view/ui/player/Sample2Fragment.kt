package io.github.ovso.hometraining.view.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.ovso.hometraining.R
import kotlinx.android.synthetic.main.fragment_sample1.*

class Sample2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_sample1, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        you_tube_player_view.play(VIDEO_ID)

    }

    companion object {
        fun newInstance() = Sample2Fragment()
        private const val VIDEO_ID = "m2SZ6RV_J7I"
    }
}
