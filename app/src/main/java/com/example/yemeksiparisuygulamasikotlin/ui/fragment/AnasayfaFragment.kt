package com.example.yemeksiparisuygulamasikotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.yemeksiparisuygulamasikotlin.R
import com.example.yemeksiparisuygulamasikotlin.data.entity.Yemekler
import com.example.yemeksiparisuygulamasikotlin.databinding.FragmentAnasayfaBinding
import com.example.yemeksiparisuygulamasikotlin.ui.adapter.YemeklerAdapter
import com.example.yemeksiparisuygulamasikotlin.ui.viewmodel.AnasayfaViewModel

class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel : AnasayfaViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)
        var filter = ArrayList<Yemekler>()
        binding.toolbarAnasayfa.title = ""

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarAnasayfa)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                Log.e("onCreateMenu","onCreateMenu")
                menuInflater.inflate(R.menu.toolbar_menu,menu)

                val item = menu.findItem(R.id.action_ara)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@AnasayfaFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        },viewLifecycleOwner, Lifecycle.State.RESUMED)


        binding.rv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        //dinleme işlemi
        viewModel.yemekListesi.observe(viewLifecycleOwner){
            Log.e("observe","observe")
            var adapter = YemeklerAdapter(requireContext(),it,viewModel)
            binding.rv.adapter = adapter
        }






        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("onCreate","onCreate")

        super.onCreate(savedInstanceState)
        val tempViewModel : AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onQueryTextSubmit(query: String): Boolean {// arama butonuna basıldıgı zaman çalışır
//        Log.e("textSubmit","textSubmit")
//        var filter  = viewModel.yemekListesi.value?.filter { (it.yemek_adi).toLowerCase().contains(query.toLowerCase()) }
//        viewModel.yemekListesi.value = filter
//        Log.e("filter list","${viewModel.yemekListesi.value }")
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean { //harf girdikçe ve sildikçe çalışır
//        Log.e("textChange","textChange")
//        var filter = viewModel.yemekListesi.value?.filter { (it.yemek_adi).toLowerCase().contains(newText.toLowerCase()) }
//        viewModel.yemekListesi.value = filter
//        Log.e("filter list","${viewModel.yemekListesi.value }")
        return true
    }




//    override fun onResume() {
//        Log.e("onResume","onResume")
//        super.onResume()
//        viewModel.yemeklerYukle()
//    }








}