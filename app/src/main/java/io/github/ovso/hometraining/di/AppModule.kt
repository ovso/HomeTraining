package io.github.ovso.hometraining.di

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
}
