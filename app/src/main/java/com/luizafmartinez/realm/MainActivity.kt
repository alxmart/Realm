package com.luizafmartinez.realm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luizafmartinez.realm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnSalvar.setOnClickListener {
            val nome = binding.editNome.text.toString()
        }






    }
}