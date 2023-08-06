package com.example.yemeksiparisuygulamasikotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.yemeksiparisuygulamasikotlin.R
import com.example.yemeksiparisuygulamasikotlin.data.entity.SepetYemekler
import com.example.yemeksiparisuygulamasikotlin.databinding.FragmentDetaySayfasiBinding
import com.example.yemeksiparisuygulamasikotlin.ui.viewmodel.DetaySayfasiViewModel
import com.google.android.material.snackbar.Snackbar
import kotlin.math.sign

class DetaySayfasiFragment : Fragment() {
    private lateinit var binding: FragmentDetaySayfasiBinding
    private lateinit var viewModel:DetaySayfasiViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detay_sayfasi, container, false)

        binding.toolbarDetay.title = ""

        val kullanici_adi = "faikcoskuncakir"

        val bundle:DetaySayfasiFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Glide.with(this).load(url).override(300,300).into(binding.ivYemekResim)
        //binding.ivYemekResim.setImageResource(resources.getIdentifier(gelenYemek.yemek_resim_adi,"drawable",requireContext().packageName))
        binding.tvYemekAdi.text = gelenYemek.yemek_adi
        binding.tvYemekFiyat.text = "${gelenYemek.yemek_fiyat}₺"
        binding.textViewAdet.text = "1"


        binding.buttonSepetEkle.setOnClickListener {
            val siparisAdet = binding.textViewAdet.text.toString().toInt()
            Snackbar.make(it,"$siparisAdet adet ${gelenYemek.yemek_adi} sepete eklendi!",Snackbar.ANIMATION_MODE_SLIDE).show()

            sepeteEkle(gelenYemek.yemek_adi,gelenYemek.yemek_resim_adi,gelenYemek.yemek_fiyat,siparisAdet, kullanici_adi)
        }

        binding.buttonSepeteGit.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.yemekSepetGecis)
        }

        binding.imageViewNegative.setOnClickListener {
            azalt()
        }

        binding.imageViewPlus.setOnClickListener {
            arttir()
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DetaySayfasiViewModel by viewModels()
        viewModel = tempViewModel
    }


    fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){
        viewModel.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }

    fun azalt(){
        var x : String = binding.textViewAdet.text.toString()
        var y = x.toInt()
        if(y != 0){
            y = y - 1
        }
        binding.textViewAdet.text = y.toString()
    }

    fun arttir(){
        var x : String = binding.textViewAdet.text.toString()
        var y = x.toInt()
        y = y + 1
        binding.textViewAdet.text = y.toString()
    }







}