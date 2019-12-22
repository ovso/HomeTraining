package io.github.ovso.hometraining.di

import io.github.ovso.hometraining.data.api.SearchRequest
import io.github.ovso.hometraining.view.ui.video.VideoAdapter
import org.koin.dsl.module

val appModule = module {
  factory { VideoAdapter() }
  single { SearchRequest() }
}