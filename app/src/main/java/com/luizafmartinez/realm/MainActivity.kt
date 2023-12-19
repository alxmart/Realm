package com.luizafmartinez.realm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.luizafmartinez.realm.database.DatabaseRealm
import com.luizafmartinez.realm.databinding.ActivityMainBinding
import com.luizafmartinez.realm.model.Usuario
import io.realm.kotlin.types.ObjectId

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val realm by lazy {
        DatabaseRealm()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener {

            val nomeRecuperado = binding.editNome.text.toString()
            val usuario = Usuario().apply {
                nome = nomeRecuperado
                idade = 10
            }
            realm.salvar( usuario )
        }


        binding.btnListar.setOnClickListener {

            val lista = realm.listar()
            var textoLista = ""
            lista.forEach() { usuario ->
                textoLista += "${usuario.nome} - idade: ${usuario.idade} \n"
                Log.i("info_realm", "id: ${usuario.id} - ${usuario.nome}")
            }

            binding.textResultado.text = textoLista
        }

        binding.btnRemover.setOnClickListener {
            //6581ce1d36882935ffcf1bc9
            var id = ObjectId.from("6581ce1d36882935ffcf1bc9")
            realm.remover(id)
        }

        binding.btnAtualizar.setOnClickListener {
            val nomeRecuperado = binding.editNome.text.toString()
            var idSelecionado = ObjectId.from("6581ce2636882935ffcf1bcc")
            val usuario = Usuario().apply {
                id = idSelecionado
                nome = nomeRecuperado
                idade = 48
            }
            realm.atualizar( usuario )
        }

    }

}
