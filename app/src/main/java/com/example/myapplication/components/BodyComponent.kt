package com.example.myapplication.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SpaceH(size: Dp=5.dp){
    Spacer(modifier = Modifier.height(size))
}

@Composable
fun SpaceW(size: Dp=5.dp){
    Spacer(modifier = Modifier.width(size))
}

@Composable
fun MainTextField(value: String, onValueChange: (String)->Unit, label: String){
    OutlinedTextField(
        value = value, 
        onValueChange = onValueChange,
        label= { Text(text = label, fontSize = 11.sp)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    )
}

@Composable
fun MainButton(text: String, color: Color = MaterialTheme.colorScheme.primary, onClick:()->Unit){
    OutlinedButton(onClick = onClick, colors=ButtonDefaults.outlinedButtonColors(
        containerColor = Color.Transparent,
        contentColor = color
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun Alert(
    title: String,
    message: String,
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDismissClick, 
        confirmButton = { Button(onClick = {onConfirmClick()}) {
            Text(text = confirmText)
        }},
        title = { Text(text = title)},
        text = { Text(text = message)}
    )
}