package com.example.myapplicationapi.data

import com.example.data.Daos.Dao
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.data.Retrofit.RetrofitServices
import kotlinx.coroutines.flow.Flow

class Repository(
    private val api: RetrofitServices,
    private val myDao: Dao
){
    // Получение списка элементов и заполнение БД
    suspend fun getItem(page: Int) {
        val itemsList: List<DataBaseModel>?
        itemsList = api.getItemList(page).body() ?: listOf()
        myDao.addAllData(itemsList)
    }

    // Поиск бо базе данных
    fun searchDataBase(searchQuery: String): Flow<List<DataBaseModel>> =
        myDao.searchDataBase(searchQuery)

    // Обновление UI при изменение БД
    fun getAllSomethingData(): Flow<List<DataBaseModel>> =
        myDao.getAllSomethingData()

    // Сортировка по имени/Описанию
    fun sortByNameOrDesc(askSort: Int): Flow<List<DataBaseModel>> =
        myDao.sortByNameOrDesc(askSort)

}