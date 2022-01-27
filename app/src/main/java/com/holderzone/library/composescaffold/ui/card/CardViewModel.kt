package com.holderzone.library.composescaffold.ui.card

import com.holderzone.library.composescaffold.base.mvvm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
public class CardViewModel @Inject constructor(
  repository: CardRepository
) : BaseViewModel()
