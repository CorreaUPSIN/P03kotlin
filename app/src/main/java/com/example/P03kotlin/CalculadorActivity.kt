package com.example.P03kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.view.View
import android.widget.Toast
import android.content.Intent
import android.app.AlertDialog
import android.content.DialogInterface

class CalculadorActivity : AppCompatActivity() {
    private lateinit var inputNum1: EditText
    private lateinit var inputNum2: EditText
    private lateinit var lblResultado: TextView
    private lateinit var lblMostrarNombreUser: TextView
    private var num1: Int = 0
    private var num2: Int = 0
    private lateinit var calculadora: Calculadora



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        // Obtener una referencia al EditText
        inputNum1 = findViewById(R.id.inputNum1)
        inputNum2 = findViewById(R.id.inputNum2)

        lblResultado = findViewById(R.id.lblResultado)
        lblMostrarNombreUser = findViewById(R.id.lblMostrarNombreUser)


        // Obtener una referencia al botón
        val btnSumar = findViewById<Button>(R.id.btnSumar)
        val btnRestar = findViewById<Button>(R.id.btnRestar)
        val btnMultiplicar = findViewById<Button>(R.id.btnMultiplicar)
        val btnDividir = findViewById<Button>(R.id.btnDividir)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)

        val bundle = intent.extras
        if (bundle != null) {
            val usuario = bundle.getString("Usuario")
            val txtUsuario = findViewById<TextView>(R.id.lblMostrarNombreUser)
            txtUsuario.text = "Usuario: $usuario"
        }

        btnSumar.setOnClickListener {
            val stringNum1 = inputNum1.text.toString()
            val stringNum2 = inputNum2.text.toString()

            if (!TextUtils.isEmpty(stringNum1) && !TextUtils.isEmpty(stringNum2)) {
                val num1 = stringNum1.toDouble()
                val num2 = stringNum2.toDouble()

                calculadora = Calculadora(num1, num2)

                // Realiza otras operaciones con la calculadora si es necesario
                val resultado = calculadora.sumar()
                val resultadoTexto = resultado.toString()
                lblResultado.text = resultadoTexto
            } else {
                Toast.makeText(applicationContext, "Llene todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }

        btnRestar.setOnClickListener {
            val stringNum1 = inputNum1.text.toString()
            val stringNum2 = inputNum2.text.toString()

            if (!TextUtils.isEmpty(stringNum1) && !TextUtils.isEmpty(stringNum2)) {
                val num1 = stringNum1.toDouble()
                val num2 = stringNum2.toDouble()

                calculadora = Calculadora(num1, num2)

                // Realiza otras operaciones con la calculadora si es necesario
                val resultado = calculadora.restar()
                val resultadoTexto = resultado.toString()
                lblResultado.text = resultadoTexto
            } else {
                Toast.makeText(applicationContext, "Llene todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }

        btnMultiplicar.setOnClickListener {
            val stringNum1 = inputNum1.text.toString()
            val stringNum2 = inputNum2.text.toString()

            if (!TextUtils.isEmpty(stringNum1) && !TextUtils.isEmpty(stringNum2)) {
                val num1 = stringNum1.toDouble()
                val num2 = stringNum2.toDouble()

                calculadora = Calculadora(num1, num2)

                // Realiza otras operaciones con la calculadora si es necesario
                val resultado = calculadora.multiplicar()
                val resultadoTexto = resultado.toString()
                lblResultado.text = resultadoTexto
            } else {
                Toast.makeText(applicationContext, "Llene todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }

        btnDividir.setOnClickListener {
            val stringNum1 = inputNum1.text.toString()
            val stringNum2 = inputNum2.text.toString()

            if (!TextUtils.isEmpty(stringNum1) && !TextUtils.isEmpty(stringNum2)) {
                val num1 = stringNum1.toDouble()
                val num2 = stringNum2.toDouble()

                if (num2 <= 0) {
                    Toast.makeText(applicationContext, "No se puede dividir entre $num2.", Toast.LENGTH_SHORT).show()
                } else {
                    calculadora = Calculadora(num1, num2)

                    // Realiza otras operaciones con la calculadora si es necesario
                    val resultado = calculadora.dividir()
                    val resultadoTexto = resultado.toString()
                    lblResultado.text = resultadoTexto
                }

            } else {
                Toast.makeText(applicationContext, "Llene todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }

        btnLimpiar.setOnClickListener {
            inputNum1.setText("")
            inputNum2.setText("")
            lblResultado.setText("")
        }

        btnCerrarSesion.setOnClickListener {
            val builder = AlertDialog.Builder(this@CalculadorActivity)
            builder.setTitle("Confirmación")
            builder.setMessage("¿Estás seguro de querer cerrar sesión?")
            builder.setPositiveButton("Sí") { dialog, _ ->
                // Acciones a realizar si se selecciona "Sí"
                val intent = Intent(this@CalculadorActivity, MainActivity::class.java)
                startActivity(intent)
                finish() // Finaliza la actividad actual (CalculadorActivity)
                dialog.dismiss()
            }
            builder.setNegativeButton("No") { dialog, _ ->
                // Acciones a realizar si se selecciona "No"
                dialog.dismiss() // Cierra el diálogo sin realizar ninguna acción adicional
            }
            val dialog = builder.create()
            dialog.show()
        }


    }

    class Calculadora(private val numero1: Double, private val numero2: Double) {

        // Resto de métodos de la clase Calculadora

        fun sumar(): Double {
            return numero1 + numero2
        }

        fun restar(): Double {
            return numero1 - numero2
        }

        fun multiplicar(): Double {
            return numero1 * numero2
        }

        fun dividir(): Double {
            if (numero2 != 0.0) {
                return numero1 / numero2
            } else {
                throw ArithmeticException("Error: No se puede dividir entre cero")
            }
        }
    }

}