package io.github.ovso.hometraining.view.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentVideoBinding
import kotlinx.android.synthetic.main.fragment_video.rv_video
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import timber.log.Timber

class VideoFragment : Fragment() {

    companion object {
        fun newInstance() = VideoFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(VideoViewModel::class.java)
            .apply {
                query = arguments?.getString("query")
                Timber.d("VideoFragment query = $query")
            }
    }

    private val adapter by lazy {
        VideoAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = generateBinding(inflater, container).root

    private fun generateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) =
        DataBindingUtil.inflate<FragmentVideoBinding>(
            inflater,
            R.layout.fragment_video,
            container,
            false
        ).apply {
            this.viewModel = this@VideoFragment.viewModel
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRv()
        observe()
    }

    private fun observe() {
        viewModel.errorDialogLive.observe(this, Observer {
            activity?.alert {
                message = it.message.toString()
                okButton {
                    it.dismiss()
                }
            }?.show()
        })
    }

    private fun setupRv() {
        with(rv_video) {
            this.adapter = this@VideoFragment.adapter
        }
    }
}
