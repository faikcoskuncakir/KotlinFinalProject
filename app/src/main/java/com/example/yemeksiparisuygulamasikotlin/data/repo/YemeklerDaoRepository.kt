package com.example.yemeksiparisuygulamasikotlin.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.yemeksiparisuygulamasikotlin.data.entity.*
import com.example.yemeksiparisuygulamasikotlin.retrofit.ApiUtils
import com.example.yemeksiparisuygulamasikotlin.retrofit.YemeklerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository {

    var ydao : YemeklerDao
    var yemekListesi : MutableLiveData<List<Yemekler>>

    init {
        ydao = ApiUtils.getYemeklerDao()
        yemekListesi = MutableLiveData()
    }

    fun yemeklerYukle(){
        ydao.tumYemekler().enqueue(object : Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>, response: Response<YemeklerCevap>) {
                val liste = response.body()!!.yemekler
                yemekListesi.value = liste
            }

            override fun onFailure(call: Call<YemeklerCevap>, t: Throwable) {}
        })

    }
    fun yemeklerGetir() : MutableLiveData<List<Yemekler>>{
        return  yemekListesi
    }











}