package com.example.myapplication.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.components.Alert
import com.example.myapplication.components.MainButton
import com.example.myapplication.components.MainTextField
import com.example.myapplication.components.SpaceH
import com.example.myapplication.components.TwoCards
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(){
    Scaffold (topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Descuentos")
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White
            )
        )
    }){
        ContentHomeView(it)
    }
}

@Composable
fun ContentHomeView(paddingValues: PaddingValues){
    Column(modifier = Modifier
        .padding(paddingValues)
        .padding(10.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var precio by remember{ mutableStateOf("") }
        var descuento by remember{ mutableStateOf("") }
        var precioDescuento by remember{ mutableStateOf("0") }
        var totalDescuento by remember{ mutableStateOf("0") }
        val numPrecio= precio.toDoubleOrNull()
        val numDescuento= descuento.toDoubleOrNull()
        var showAlert by remember {mutableStateOf(false)}

        TwoCards(title1 = "Total", number1 = totalDescuento , title2 = "Descuento" , number2 = precioDescuento )
        SpaceH(20.dp)
        MainTextField(value = precio,  onValueChange = {
            precio = it.replace(',', '.')
        }, label = "Escribe el precio")
        SpaceH()
        MainTextField(value = descuento, onValueChange = {
            descuento = it.replace(',', '.')
        }, label = "Escribe el descuento en %")
        SpaceH(10.dp)
        MainButton("Generar Descuento") {
        if(numPrecio!= null && numPrecio>0 && numDescuento!=null &&numDescuento>0){
            val pDescuento = calcularPrecio(precio.toDouble(), descuento.toDouble())
            precioDescuento = if (pDescuento % 1 == 0.0) {
                val prDescuento = pDescuento.toInt()
                prDescuento.toString()
            } else {
                String.format(Locale.US, "%.2f", pDescuento)
            }
            val tDescuento = calcularDescuento(precio.toDouble(), descuento.toDouble())
            totalDescuento = if (tDescuento % 1 == 0.0) {
                val totDescuento = tDescuento.toInt()
                totDescuento.toString()
            } else {
                String.format(Locale.US, "%.2f", tDescuento)
            }
        }else {
            showAlert = true
        }
    }

        SpaceH()
        MainButton(text = "Limpiar", color = Color.Red) {
            precio = ""
            descuento = ""
            totalDescuento = "0"
            precioDescuento = "0"
        }

        if(showAlert){
            Alert(
                title = "Error" ,
                message = "Debes escribir dos números positivos tanto en 'Precio' como en 'Descuento'.",
                confirmText = "Aceptar",
                onConfirmClick = {showAlert = false })
            {
                //para realizar otra acción al momento de quitar la alerta, en este caso no es necesario.
            }
        }
    }

}

fun calcularPrecio(precio: Double, descuento: Double): Double{
    return precio - calcularDescuento(precio,descuento)
}

fun calcularDescuento(precio: Double, descuento: Double): Double{
    return precio * (1- descuento/100)
}

