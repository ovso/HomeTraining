package io.github.ovso.hometraining.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.GenderFragmentBinding

class GenderFragment : Fragment() {

    companion object {
        fun newInstance() = GenderFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(GenderViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = generateBinding(inflater, container).root

    private fun generateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DataBindingUtil.inflate<GenderFragmentBinding>(
            inflater,
            R.layout.gender_fragment,
            container,
            false
        ).apply {
            this.viewModel = this@GenderFragment.viewModel
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
