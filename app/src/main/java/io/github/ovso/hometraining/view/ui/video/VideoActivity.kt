package io.github.ovso.hometraining.view.ui.video

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityVideoBinding
import io.github.ovso.hometraining.view.base.DataBindingActivity2
import kotlinx.android.synthetic.main.activity_video.rv_video
import kotlinx.android.synthetic.main.activity_video.toolbar
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.ext.android.inject

class VideoActivity : DataBindingActivity2<ActivityVideoBinding>(
    layoutResId = R.layout.activity_video,
    viewModelCls = VideoViewModel::class.java
) {

    private val adapter: VideoAdapter by inject()
    private val itemDivider: DividerItemDecoration by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        setupRv()
        observe()
        setupAds()
    }

    private fun setupAds() {
        val adView = AdView(this)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        ad_container.addView(adView)
    }

    private fun observe() {
        val owner = this
        binding.viewModel?.itemsLive?.observe(owner, Observer {
            adapter.submitList(it.toMutableList())
        })
    }

    private fun setupRv() {
        with(rv_video) {
            addItemDecoration(itemDivider)
            adapter = this@VideoActivity.adapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}
