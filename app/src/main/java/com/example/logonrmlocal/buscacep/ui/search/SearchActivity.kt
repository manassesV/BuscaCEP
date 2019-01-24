 package com.example.logonrmlocal.buscacep.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.logonrmlocal.buscacep.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_search.*


 class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var pesquisaViewModel: PesquisaViewModel



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        Picasso.get().load("https://cdn4.iconfinder.com/data/icons/user-icon-2/100/user-23-512.png").into(ivLogo)

        pesquisaViewModel = ViewModelProviders.of(this)
                .get(PesquisaViewModel::class.java)


        btPesquisar.setOnClickListener({
            pesquisaViewModel.buscar(etCEP.text.toString())
        })


        pesquisaViewModel.endereco.observe(this, Observer {
            tvResultado.text = it?.logradouro
        })

        pesquisaViewModel.mensagemErro.observe(this, Observer {
             if(!it.equals("")){
                 Toast.makeText(this, it, Toast.LENGTH_LONG).show()
             }
        })


        pesquisaViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading == true) {
                loading.visibility = View.VISIBLE
            } else {
                loading.visibility = View.GONE
            }

        })
    }


 }
