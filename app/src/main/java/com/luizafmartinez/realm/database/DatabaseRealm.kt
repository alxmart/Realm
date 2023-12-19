package com.luizafmartinez.realm.database

import com.luizafmartinez.realm.model.Usuario
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.ObjectId

class DatabaseRealm {

    private val configuracao = RealmConfiguration.Builder(
        schema = setOf( Usuario::class)
    )

    private val realm = Realm.open( configuracao.build() )
    fun salvar( usuario: Usuario ) {

        realm.writeBlocking {
            copyToRealm( usuario)
        }
    }


    fun listar() : RealmResults<Usuario> {

        return realm
           //.query<Usuario>( "nome == $0 and idade >= $1", "jamilton", "10" )
           // .query<Usuario>( "nome == $0", "alex" )
           .query<Usuario>()
            //.sort("nome", Sort.ASCENDING)  -> é o padrão, não precisa colocar
            // count / min , max, distinct , first, limit
           .find()
    }

    fun remover(id: ObjectId) {

        realm.writeBlocking {
            val usuarioRemover = query<Usuario>("id == $0", id)
                .find()
                .first()
            delete(usuarioRemover)
        }
    }

    fun atualizar( usuario: Usuario) {

        realm.writeBlocking {
            val usuarioAtualizar = query<Usuario>("id == $0", usuario.id)
                .find()
                .first()

            usuarioAtualizar.nome = usuario.nome
            usuarioAtualizar.idade = usuario.idade
        }
    }
}



