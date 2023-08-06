package com.example.yemeksiparisuygulamasikotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeksiparisuygulamasikotlin.R
import com.example.yemeksiparisuygulamasikotlin.data.entity.SepetYemekler
import com.example.yemeksiparisuygulamasikotlin.databinding.FragmentSepetSayfasiBinding
import com.example.yemeksiparisuygulamasikotlin.ui.adapter.SepetYemeklerAdapter
import com.example.yemeksiparisuygulamasikotlin.ui.viewmodel.SepetSayfasiViewModel
import com.google.android.material.snackbar.Snackbar

class SepetSayfasiFragment : Fragment() {
    private lateinit var binding: FragmentSepetSayfasiBinding
    private lateinit var viewModel:SepetSayfasiViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sepet_sayfasi, container, false)

        binding.toolbarSepet.title = ""


        binding.rvSepet.layoutManager = LinearLayoutManager(requireContext())

        viewModel.sepetYemekListesi.observe(viewLifecycleOwner){
            val adapter = SepetYemeklerAdapter(requireContext(),it,viewModel)
            binding.rvSepet.adapter = adapter
            binding.tvToplamFiyat.text  = "${viewModel.toplam()}₺"

        }



        binding.butonSepetOnayla.setOnClickListener {

            Snackbar.make(it,"Sepetiniz onaylandı! İyi günler.",Snackbar.LENGTH_LONG).show()
            viewModel.toplam()
        }



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:SepetSayfasiViewModel by viewModels()
        viewModel = tempViewModel
    }

//    fun sepetYemekSil(sepet_yemek_id:Int,kullanici_adi:String){
//        viewModel.sepetYemekSil(sepet_yemek_id,kullanici_adi)
//    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetYemekYukle("faikcoskuncakir")
//        binding.tvToplamFiyat.text = "${viewModel.toplam()}₺"
    }

}