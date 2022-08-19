package com.example.data.Daos

import androidx.room.*
import androidx.room.Dao
import com.example.data.models.DataBaseModel
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {

    // получение всей БД
    @Query("SELECT * FROM my_table")
    fun getAllSomethingData(): Flow<List<DataBaseModel>>

    // Получение отсортированной БД
    @Query("SELECT * FROM my_table ORDER BY " +
            "CASE WHEN :isAsc = 1  THEN title END ASC, " +
            "CASE WHEN :isAsc = 0 THEN title END DESC ")
    fun sortByNameOrDesc(isAsc: Int): Flow<List<DataBaseModel>>

    // Добавление данных в БД
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllData(newData: List<DataBaseModel>)

    // Получение элементов в соответствии с запросом
    @Query("SELECT * FROM my_table WHERE title LIKE '%' || :searchQuery  || '%'")
    fun searchDataBase(searchQuery: String): Flow<List<DataBaseModel>>
}