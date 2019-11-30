package kr.co.mybroccoli.transfer.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class DataBindingActivity<T : ViewDataBinding, V : DisposableViewModel> : AppCompatActivity() {

  private lateinit var viewDataBinding: T

  @LayoutRes
  abstract fun getLayoutId(): Int

  abstract fun getVariableValue(): V

  abstract fun getVariableId(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    performDataBinding()
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