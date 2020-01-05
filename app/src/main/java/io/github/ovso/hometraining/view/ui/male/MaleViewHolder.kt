package io.github.ovso.hometraining.view.ui.male

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.hometraining.BR
import io.github.ovso.hometraining.R

class MaleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_male, parent, false)
) {

    private val binding = DataBindingUtil.bind<ViewDataBinding>(itemView)!!

    fun onBindViewHolder(item: MaleItem) {
        binding.run {
            setVariable(BR.item, item)
            executePendingBindings()
        }
    }
}
