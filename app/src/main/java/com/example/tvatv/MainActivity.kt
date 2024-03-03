package com.example.tvatv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tvatv.ui.theme.TvatvTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvatvTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TVACalculator()
                }
            }
        }
    }
}

@Composable
fun TVACalculator() {
    var unitPrice by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var includeVAT by remember { mutableStateOf(false) }
    var totalAmount by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = unitPrice,
            onValueChange = { unitPrice = it },
            label = { Text("Prix unitaire") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantité des produits") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Inclure TVA", modifier = Modifier.padding(end = 8.dp))
            Switch(
                checked = includeVAT,
                onCheckedChange = { includeVAT = it },
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Button(
            onClick = {
                val price = unitPrice.toDoubleOrNull() ?: 0.0
                val qty = quantity.toIntOrNull() ?: 0
                val vat = if (includeVAT) 0.20 else 0.0
                totalAmount = price * qty * (1 + vat)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Calculer")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Montant à payer : $totalAmount",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TVACalculatorPreview() {
    TvatvTheme {
        TVACalculator()
    }
}
