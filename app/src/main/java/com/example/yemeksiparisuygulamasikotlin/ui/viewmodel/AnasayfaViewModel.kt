package com.example.yemeksiparisuygulamasikotlin.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisuygulamasikotlin.data.entity.Yemekler
import com.example.yemeksiparisuygulamasikotlin.data.repo.SepetYemeklerDaoRepository
import com.example.yemeksiparisuygulamasikotlin.data.repo.YemeklerDaoRepository

class AnasayfaViewModel : ViewModel() {
    val repo = YemeklerDaoRepository()

    var yemekListesi : MutableLiveData<List<Yemekler>>

    init {
        yemeklerYukle() // ilk çalışmada veri getirmesi için yazzdım
        yemekListesi = repo.yemeklerGetir()
    }

    fun yemeklerYukle(){
        repo.yemeklerYukle()
    }


}