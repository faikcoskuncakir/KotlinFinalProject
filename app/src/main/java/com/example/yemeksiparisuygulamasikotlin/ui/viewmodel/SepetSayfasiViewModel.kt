package com.example.yemeksiparisuygulamasikotlin.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisuygulamasikotlin.data.entity.SepetYemekler
import com.example.yemeksiparisuygulamasikotlin.data.repo.SepetYemeklerDaoRepository
import com.example.yemeksiparisuygulamasikotlin.data.repo.YemeklerDaoRepository

class SepetSayfasiViewModel : ViewModel() {
    var rrr = SepetYemeklerDaoRepository()

    var sepetYemekListesi : MutableLiveData<List<SepetYemekler>>

    init {
        sepetYemekYukle(kullanici_adi = "faikcoskuncakir")
        sepetYemekListesi = rrr.sepetYemekGetir()
    }

    fun sepetYemekYukle(kullanici_adi:String){
        rrr.sepetYemekYukle(kullanici_adi)
        toplam()
    }
    fun sepetYemekSil(sepet_yemek_id:Int,kullanici_adi:String){
        rrr.sepetYemekSil(sepet_yemek_id,kullanici_adi)
    }

    fun toplam() : Int{
        var toplamFiyat = 0
        var sepetYemekList : List<SepetYemekler>

        try {
            sepetYemekList =
                sepetYemekListesi.value.orEmpty()

            for(sepetYemek in sepetYemekList){
                toplamFiyat += (sepetYemek.yemek_fiyat * sepetYemek.yemek_siparis_adet)
            }
        }
        catch (ex:NullPointerException){
            ex.printStackTrace()
        }

        return toplamFiyat
    }

}