package com.example.myapplicationapi.MainFragment
import androidx.lifecycle.*
import com.example.data.models.DataBaseModel
import com.example.data.repositories.SomethingRepository
import com.example.myapplicationapi.Data.Retrofit.Common
import com.example.myapplicationapi.Data.Retrofit.RetrofitServices
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyViewModel: ViewModel() {

    val integer: MutableLiveData<Int> = MutableLiveData(1)
    var askSort: Int = 0

    private var mService: RetrofitServices = Common.retrofitService
    init {
            getAllItemList(1)
    }

    fun observeAllSomething(): LiveData<List<DataBaseModel>> =
        SomethingRepository.instance.getAllSomethingData().asLiveData()

    fun searchDatabase(searchQuery: String): LiveData<List<DataBaseModel>> =
         SomethingRepository.instance.searchDataBase(searchQuery).asLiveData()

    fun getAllItemList(page: Int) {
        mService.getItemList(page).enqueue(object : Callback<List<DataBaseModel>> {
            override fun onFailure(call: Call<List<DataBaseModel>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<DataBaseModel>>, response: Response<List<DataBaseModel>>) {
                viewModelScope.launch {
                    val listData: List<DataBaseModel> = response.body()!!
                    SomethingRepository.instance.addAllData(listData)
                    integer.postValue(integer.value!! + 1)
                }
            }
        })
    }

    fun observeSortByName(): LiveData<List<DataBaseModel>>{
        askSort = if (askSort == 0) //это криво давай нормально сделаем
            1
        else 0
        return SomethingRepository.instance.sortByName(askSort).asLiveData()
    }

}