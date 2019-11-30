package io.github.ovso.hometraining.view.ui.main

import androidx.fragment.app.Fragment
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.view.ui.female.FemaleFragment
import io.github.ovso.hometraining.view.ui.main.BottomNavPosition.FEMALE
import io.github.ovso.hometraining.view.ui.main.BottomNavPosition.MALE
import io.github.ovso.hometraining.view.ui.main.BottomNavPosition.POPULAR
import io.github.ovso.hometraining.view.ui.male.MaleFragment
import io.github.ovso.hometraining.view.ui.pop.PopularFragment

enum class BottomNavPosition(
  val position: Int,
  val id: Int
) {
  MALE(0, R.id.bottom_nv_male),
  FEMALE(1, R.id.bottom_nv_female),
  POPULAR(2, R.id.bottom_nv_popular)
}

fun findNavigationPositionById(id: Int): BottomNavPosition = when (id) {
  MALE.id -> MALE
  FEMALE.id -> FEMALE
  POPULAR.id -> POPULAR
  else -> MALE
}

fun BottomNavPosition.createFragment(): Fragment = when (this) {
  MALE -> MaleFragment.newInstance()
  FEMALE -> FemaleFragment.newInstance()
  POPULAR -> PopularFragment.newInstance()
}

fun BottomNavPosition.getTag(): String = when (this) {
  MALE -> MaleFragment.TAG
  FEMALE -> FemaleFragment.TAG
  POPULAR -> PopularFragment.TAG
}