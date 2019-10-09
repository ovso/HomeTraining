package io.github.ovso.hometraining.view.gender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.ovso.hometraining.R
import io.reactivex.disposables.CompositeDisposable

class PopularFragment : Fragment() {

  private val compositeDisposable = CompositeDisposable()

  companion object {
    fun newInstance(position: Int): PopularFragment {
      return PopularFragment().apply {
        arguments = Bundle().apply { putInt("position", position) }
      }
    }
  }

  private val viewModel by lazy {
    ViewModelProviders.of(this)
        .get(PopularViewModel::class.java)
        .apply {
          type = arguments?.getInt("position") ?: 0
        }
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
    DataBindingUtil.inflate<PopularFragment>(
        inflater,
        R.layout.fragment_popular,
        container,
        false
    ).apply {
      this.viewModel = this@PopularFragment.viewModel
    }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
  }

  override fun onDetach() {
    super.onDetach()
    compositeDisposable.clear()
  }
}