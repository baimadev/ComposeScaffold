package com.holderzone.library.composescaffold.ui.card

import com.holderzone.library.composescaffold.base.BaseRepository
import javax.inject.Inject

public class CardRepository @Inject constructor(
  source: CardSource
) : BaseRepository()
