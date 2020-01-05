package io.github.ovso.hometraining.di

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.view.ui.female.FemaleAdapter
import io.github.ovso.hometraining.view.ui.male.MaleAdapter
import io.github.ovso.hometraining.view.ui.video.VideoAdapter
import org.koin.dsl.module

val appModule = module {
    single { SearchRequest() }
    factory { MaleAdapter() }
    factory { FemaleAdapter() }
    factory { VideoAdapter() }
    single {
        DividerItemDecoration(get(), DividerItemDecoration.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(get(), R.drawable.all_divider)!!)
        }
    }
}
