package io.github.ovso.hometraining.view.ui.video

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.gms.ads.AdRequest
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.ActivityVideoBinding
import io.github.ovso.hometraining.view.base.DataBindingActivity2
import kotlinx.android.synthetic.main.activity_video.*
import kotlinx.android.synthetic.main.layout_ads_banner.*
import org.koin.android.ext.android.inject

class VideoActivity : DataBindingActivity2<ActivityVideoBinding>(
    layoutResId = R.layout.activity_video,
    viewModelCls = VideoViewModel::class.java
) {

    private val adapter: VideoAdapter by inject()
    private val itemDivider: DividerItemDecoration by inject()
    private val adRequest: AdRequest by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        setupRv()
        observe()
        all_ads_banner.loadAd(adRequest)
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
