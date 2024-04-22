package com.example.myapplication.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TwoCards(title1: String, number1: String, title2: String, number2:String){
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        MainCard(title = title1, number = number1, modifier = Modifier
            .padding(start = 30.dp)
            .weight(1f)
        )
        SpaceW(10.dp)
        MainCard(title = title2, number = number2, modifier = Modifier
            .padding(end = 30.dp)
            .weight(1f)
        )
    }
}



@Composable
fun MainCard(title: String, number: String, modifier: Modifier = Modifier){
    val isDarkTheme = isSystemInDarkTheme()
    val isContainerColor= if (isDarkTheme) Color(0xFF90C7E0) else Color(0xFF00B0FF)
    Card (
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = isContainerColor
        )
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = title, color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = number, color = Color.Black, fontSize = 18.sp)
        }
    }
}