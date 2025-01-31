package gonzalez.jesus.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Se usa lateinit ya que esta se inicializara de forma tardia, en otro momento en la ejecucion
    private lateinit var resultadoTv: TextView
    private var input = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultadoTv = findViewById(R.id.display)

        // Se mapean botones en una lista, para mejor manejo
        val botones = listOf(
            R.id.siete, R.id.ocho, R.id.nueve,
            R.id.cuatro, R.id.cinco, R.id.seis,
            R.id.uno, R.id.dos, R.id.tres,
            R.id.cero, R.id.punto
        )

        // Asignamos los números y punto a la entrada
        botones.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener { clickEnBoton(it) }
        }

        // Operadores
        findViewById<Button>(R.id.mas).setOnClickListener { clickEnOperador("+") }
        findViewById<Button>(R.id.resta).setOnClickListener { clickEnOperador("-") }
        findViewById<Button>(R.id.multiplicacion).setOnClickListener { clickEnOperador("*") }
        findViewById<Button>(R.id.division).setOnClickListener { clickEnOperador("/") }

        // Igual y borrar
        findViewById<Button>(R.id.igual).setOnClickListener { clickEnIgual() }
        findViewById<Button>(R.id.borrar).setOnClickListener { clickEnBorrar() }
    }

    // Lógica para cuando se hace clic en un número o el punto
    private fun clickEnBoton(vista: View) {
        val boton = vista as Button
        input += boton.text.toString()  // Añade el número al input
        resultadoTv.text = input  // Muestra el texto en el TextView
    }

    // Lógica para cuando se hace clic en un operador
    private fun clickEnOperador(operator: String) {
        input += " $operator "  // Agrega el operador al input
        resultadoTv.text = input  // Muestra el texto en el TextView
    }

    // Lógica para cuando se hace clic en igual
    private fun clickEnIgual() {
        try {
            val resultado = realizarOperacion(input)  // Calcula la expresión
            resultadoTv.text = resultado.toString()  // Muestra el resultado
            input = resultado.toString()  // Guarda el resultado para continuar calculando
        } catch (e: Exception) {
            resultadoTv.text = "Error"  // Si hay un error en la expresión
        }
    }

    // Funcion de borrar display
    private fun clickEnBorrar() {
        input = ""
        resultadoTv.text = ""
    }

    // Función para evaluar la expresión, no al 100
    private fun realizarOperacion(expr: String): Double {
        val partes = expr.split(" ")
        if (partes.size == 3) {
            val num1 = partes[0].toDouble()
            val operador = partes[1]
            val num2 = partes[2].toDouble()

            return when (operador) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> num1 / num2
                else -> 0.0
            }
        }
        return 0.0
    }
}
