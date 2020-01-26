package io.github.ovso.hometraining.view.ui.male

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentMaleBinding
import io.github.ovso.hometraining.view.base.DataBindingFragment2
import kotlinx.android.synthetic.main.fragment_male.rv_male
import org.koin.android.ext.android.inject

class MaleFragment : DataBindingFragment2<FragmentMaleBinding>(
    layoutResId = R.layout.fragment_male,
    viewModelCls = MaleViewModel::class.java
) {

    private val adapter: MaleAdapter by inject()

    companion object {
        val TAG: String = MaleFragment::class.java.simpleName
        fun newInstance() = MaleFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRv()
        observe()
    }

    private fun observe() {
        binding.viewModel?.itemsLive?.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun setupRv() {
        rv_male.apply {
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            this.adapter = this@MaleFragment.adapter
        }
    }
}
