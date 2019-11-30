package io.github.ovso.hometraining.view.ui.male

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.view.ui.male.MaleRvAdapter.MyViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_male.tv_male_item_title

class MaleRvAdapter : RecyclerView.Adapter<MyViewHolder>() {
  val titles = mutableListOf<String>()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ) = MyViewHolder.create(parent)

  override fun getItemCount() = titles.count()

  override fun onBindViewHolder(
    holder: MyViewHolder,
    position: Int
  ) {
    holder.bind(title = titles[position])
  }

  class MyViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!),
      LayoutContainer {

    fun bind(title: String) {
      tv_male_item_title.text = title
    }

    companion object {
      fun create(parent: ViewGroup): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_male, parent, false)
        )
      }
    }
  }

}