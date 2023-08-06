package com.example.yemeksiparisuygulamasikotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisuygulamasikotlin.R
import com.example.yemeksiparisuygulamasikotlin.data.entity.Yemekler
import com.example.yemeksiparisuygulamasikotlin.databinding.YemekCardTasarimBinding
import com.example.yemeksiparisuygulamasikotlin.ui.fragment.AnasayfaFragmentDirections
import com.example.yemeksiparisuygulamasikotlin.ui.viewmodel.AnasayfaViewModel
import com.google.android.material.snackbar.Snackbar

class YemeklerAdapter(var mContext:Context,var yemekListesi:List<Yemekler>,viewModel:AnasayfaViewModel)
    : RecyclerView.Adapter<YemeklerAdapter.YemekCardTasarimTutucu>() {

    inner class YemekCardTasarimTutucu(var binding:YemekCardTasarimBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekCardTasarimTutucu {
        val binding : YemekCardTasarimBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.yemek_card_tasarim,parent,false)
        return YemekCardTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return yemekListesi.size
    }

    override fun onBindViewHolder(holder: YemekCardTasarimTutucu, position: Int) {
        val yemek = yemekListesi.get(position)
        val t = holder.binding
        t.textViewYemek.text = yemek.yemek_adi
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(300,300).into(t.imageViewYemek)
//        t.imageViewYemek.setImageResource(mContext.resources.getIdentifier(yemek.yemek_resim_adi,"drawable",mContext.packageName))
        t.textViewFiyat.text = "${yemek.yemek_fiyat}₺"


        t.cardViewYemek.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecis(yemek=yemek)
            Navigation.findNavController(it).navigate(gecis)
        }
    }


}