package com.example.P03kotlin

import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.view.View
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.content.DialogInterface


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener los valores de strings.xml
        val usuario = resources.getString(R.string.Usuario)
        val contraseña = resources.getString(R.string.Contraseña)
        val nombreUsuario = resources.getString(R.string.nombreUsuario)

// Obtener una referencia al EditText
        val inputUser = findViewById(R.id.inputUser) as EditText
        val inputPass = findViewById(R.id.inputPass) as EditText

// Obtener una referencia al botón
        val btnLogin = findViewById(R.id.btnLogin) as Button
        val btnSalir = findViewById(R.id.btnSalir) as Button

// Hacer algo con el botón obtenido
        btnLogin.setOnClickListener {
            // Obtener el contenido del EditText
            val nombreUsuario = inputUser.text.toString()
            val passUsuario = inputPass.text.toString()

            if (nombreUsuario == usuario && passUsuario == contraseña) {
                val bundle = Bundle()
                bundle.putString("Usuario", nombreUsuario)

                Toast.makeText(applicationContext, "Bienvenido", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, CalculadorActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Usuario: $usuario, Contraseña: $contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        btnSalir.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Confirmación")
            builder.setMessage("¿Estás seguro de querer salir?")
            builder.setPositiveButton("Sí") { dialog, _ ->
                // Acciones a realizar si se selecciona "Sí"
                finishAffinity() // Cierra todas las actividades y sale de la aplicación
            }
            builder.setNegativeButton("No") { dialog, _ ->
                // Acciones a realizar si se selecciona "No"
                dialog.dismiss() // Cierra el diálogo sin realizar ninguna acción adicional
            }
            val dialog = builder.create()
            dialog.show()
        }

    }
}