package io.github.ovso.hometraining.view.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.view.ui.video.model.VideoItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_ads_banner.*
import timber.log.Timber

class AdsViewHolder(
    override val containerView: View?
) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {


    fun bind(_item: VideoItem) {
        Timber.d(_item.toString())
        all_ads_banner.loadAd(AdRequest.Builder().build())
    }

    companion object {
        fun from(parent: ViewGroup): AdsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_ads_banner,
                    parent,
                    false
                )

            return AdsViewHolder(view)
        }
    }
}
