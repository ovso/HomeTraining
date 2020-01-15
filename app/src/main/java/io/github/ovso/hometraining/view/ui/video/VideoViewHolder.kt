package io.github.ovso.hometraining.view.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.hometraining.BR
import io.github.ovso.hometraining.view.ui.video.model.VideoItem

class VideoViewHolder(
    parent: ViewGroup,
    @LayoutRes layoutRes: Int
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(
            layoutRes,
            parent,
            false
        )
) {

    private val binding = DataBindingUtil.bind<ViewDataBinding>(itemView)

    fun onBindViewHolder(_item: VideoItem) {
        binding?.run {
            setVariable(BR.item, _item)
            executePendingBindings()
        }
    }
}
