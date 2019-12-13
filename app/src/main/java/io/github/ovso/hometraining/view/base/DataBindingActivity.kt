package io.github.ovso.hometraining.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlinx.android.synthetic.main.app_bar_main.toolbar

abstract class DataBindingActivity<T : ViewDataBinding, V : DisposableViewModel> : AppCompatActivity() {

  private lateinit var viewDataBinding: T

  @LayoutRes
  abstract fun getLayoutId(): Int

  abstract fun getVariableValue(): V

  abstract fun getVariableId(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    performDataBinding()
    setSupportActionBar(toolbar)
  }

  private fun performDataBinding() {
    viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
    with(viewDataBinding) {
      lifecycleOwner = this@DataBindingActivity
      setVariable(getVariableId(), getVariableValue())
      executePendingBindings()
    }
  }

}