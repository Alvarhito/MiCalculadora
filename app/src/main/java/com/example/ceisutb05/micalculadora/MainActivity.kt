package com.example.ceisutb05.micalculadora

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.httpPost
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var num1 = ""
    var num2 = ""
    var operador = ""
    var opnet=""
    var resultado = 0
    var resultadoApi="0"
    var segundo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBtn()
    }

    fun onClick(v: Button) {
        //Optengo el valor del boton presionado para saber si quiere hacer una operacion
        if (v.getText() == "+" || v.getText() == "-" || v.getText() == "x" || v.getText() == "/") {
            segundo = true
            operador = v.getText().toString()
            editText.text.insert(editText.getText().toString().length, v.getText())


        } else if (v.getText() == "=") {
            //si es igual, se hace la opearcion y revuelve el resultado en la pantalla
            operacion()
            api()

        } else if (v.getText() == "c") {
            //solo para eliminar todo lo que tiene escrito en la pantalla
            editText.text.clear()
            num1 = ""
            num2 = ""
            operador = ""
            resultado = 0
            segundo = false
        } else {
            //si no es ningun caracter de operacion o borrado, entonces es un numero, lo cual se va almacenando como num1 o num2 dependiendo si ya se escribio un operador o no
            if (!segundo) {
                num1 += v.getText()
                editText.text.insert(editText.getText().toString().length, v.getText())
            } else {
                num2 += v.getText()
                editText.text.insert(editText.getText().toString().length, v.getText())
            }
        }


    }

    fun operacion() {
        //se verifica cual es la operacion que se quiere hacer y se hace
        if (operador == "+") {
            opnet="sum"
            resultado = num1.toInt() + num2.toInt()
        } else if (operador == "-") {
            opnet="res"
            resultado = num1.toInt() - num2.toInt()
        } else if (operador == "x") {
            opnet="mul"
            resultado = num1.toInt() * num2.toInt()
        } else if (operador == "/") {
            opnet="div"
            resultado = num1.toInt() / num2.toInt()
        } else {
            resultado = num1.toInt()
        }

        //el resultrado de la operacion lo muestra en un layaout


        val tv_dynamic = TextView(this)
        tv_dynamic.textSize = 25f
        tv_dynamic.text = "==> Local: " + num1 + " " + operador + " " + num2 + "= " + resultado.toString()
        //tv_dynamic.setTextColor(Color.WHITE)
        log.addView(tv_dynamic)
    }


    fun api() {
        //creo la lista de lo que le enviare a la api con los datos
        val abecedario: HashMap<String, String> = hashMapOf("operation" to opnet,"num1" to num1,"num2" to num2)
        val url = "http://parcial.getsandbox.com/operation"
        //Envio la peticion a la url de arriba
        url.httpPost(abecedario.toList()).responseJson {request, response, result ->
            //do something with response
            when (result) {
                is Result.Failure -> {
                    Toast.makeText(this, " Error de Conexion", Toast.LENGTH_LONG).show()
                }
                is Result.Success -> {
                    val data = result.get()
                    organizar(data)
                    val tv_dynamic = TextView(this)
                    tv_dynamic.textSize = 25f
                    tv_dynamic.text = "==> Api: " + num1 + " " + operador + " " + num2 + "= " + resultadoApi.toString()
                    //tv_dynamic.setTextColor(Color.WHITE)
                    log.addView(tv_dynamic)

                }
            }

        }
    }
    fun organizar(info: Json){
        //Ya obtenida la API saco de ella el resultado con la llave 'result' y listo
        var aux: JSONObject = JSONObject()
        var auxi=""
        aux=info.obj()
        try {
            var auxi=aux.getInt("result")

        }catch (e: JSONException){
            e.printStackTrace()
            Toast.makeText(this, "nada", Toast.LENGTH_LONG).show()
        }
        resultadoApi=aux["result"].toString()
        Toast.makeText(this, aux["result"].toString(), Toast.LENGTH_LONG).show()
    }

    fun setBtn() {
        button.setOnClickListener{
            onClick(button)
        }
        button2.setOnClickListener{
            onClick(button2)
        }
        button3.setOnClickListener{
            onClick(button3)
        }
        button4.setOnClickListener{
            onClick(button4)
        }
        button5.setOnClickListener{
            onClick(button5)
        }
        button6.setOnClickListener{
            onClick(button6)
        }
        button7.setOnClickListener{
            onClick(button7)
        }
        button8.setOnClickListener{
            onClick(button8)
        }
        button9.setOnClickListener{
            onClick(button9)
        }
        button10.setOnClickListener{
            onClick(button10)
        }
        button11.setOnClickListener{
            onClick(button11)
        }
        button12.setOnClickListener{
            onClick(button12)
        }
        button13.setOnClickListener{
            onClick(button13)
        }
        button14.setOnClickListener{
            onClick(button14)
        }
        button15.setOnClickListener{
            onClick(button15)
        }
        button16.setOnClickListener{
            onClick(button16)
        }


    }
}
