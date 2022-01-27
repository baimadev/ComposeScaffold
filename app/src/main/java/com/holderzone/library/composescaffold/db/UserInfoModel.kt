package com.holderzone.library.composescaffold.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfoModel(
    @PrimaryKey val account:String,
    val pwd:String
)