package com.slowmotion.kisahnabiapp.view.content

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.slowmotion.kisahnabiapp.view.content.DetailActivity
import com.slowmotion.kisahnabiapp.R
import com.slowmotion.kisahnabiapp.model.ResponseMain
import com.slowmotion.kisahnabiapp.viewModel.ViewModelMain
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModelMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Kisah 25 Nabi"

        initView()
        AttachObserve()
    }

    private fun AttachObserve() {
        viewModel.responGetData.observe(this, Observer {showData(it)})
        viewModel.isError.observe(this, Observer {showError(it)})
        viewModel.isLoading.observe(this, Observer {showLoading(it)})

    }

    private fun showLoading(it: Boolean?) {
        if (it == true){
            progress_main.visibility = View.VISIBLE
        }else {
            progress_main.visibility = View.GONE
        }
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(this, it?.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showData(it: List<ResponseMain>) {
        val adapter = AdapterMain(it, object : AdapterMain.OnClickListener{
            override fun detail(item: ResponseMain) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("data" ,item)
                startActivity(intent)
            }
        })
        rv_main.adapter = adapter
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(ViewModelMain::class.java)
        viewModel.getDataView()
    }
}