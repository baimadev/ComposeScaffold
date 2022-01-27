package com.holderzone.library.composescaffold.db.dao

import androidx.room.*
import com.holderzone.library.composescaffold.db.UserInfoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInfoDao {

    @Query("SELECT * FROM UserInfoModel")
    fun getAll(): Flow<List<UserInfoModel>>

    @Update
    suspend fun updateUserInfoModel(UserInfoModel: UserInfoModel)

    @Insert
    suspend fun insertAll(UserInfoModel : UserInfoModel)

    @Delete
    suspend fun delete(user: UserInfoModel)
}