package com.example.currencycalculator

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencycalculator.ui.theme.CurrencyCalculatorTheme

//quando definiamo strings we have to write theme in string.xml file
//build gradle script is also important
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { //tutto ciò che sta in onCreate verrà eseguito
        super.onCreate(savedInstanceState)
        setContent { //set Contect accetta functions as parameters colling the functions
            CurrencyCalculatorTheme { //theme for define the UI
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CalculatorScreen()
                }
            }
        }
    }
}
@Composable //all the composable are function in android; used state variables and recalculate each time there is a changement
fun CalculatorScreen() {
    var eur by remember {mutableStateOf("")} //one way to declare state varable thath can change is state
    //all the informations ask to the user are string
    var gbp by remember {mutableStateOf(0.0f)}

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Text(
            text = stringResource(R.string.currency_calculator), //we need to name the parameters
            color = MaterialTheme.colors.primary,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(8.dp)) //space between the thing
        //devo metterli in colonna se no vanno uno sopra l'altro
        OutlinedTextField(
            label = {Text(text = stringResource(R.string.enter_euros))}, //label is text composable, serve per scrivere nel container
            //quando il text field è ancora vuoto
            value = eur,
            onValueChange = { eur = it.replace(',', '.') },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            //text = gbp.toString(),
            text = stringResource(R.string.result, String.format("%.2f",gbp).replace(',','.')),
            modifier = Modifier.padding(start = 16.dp)
        )
        Button(
            onClick = {
                val eurosAsNumber: Float? =
                    eur.toFloatOrNull() //if the string is not somithing traslatable in float it will be null
                if (eurosAsNumber != null) {
                    gbp = eurosAsNumber * 0.88f
                } else gbp = 0.00f
            },
            modifier = Modifier.fillMaxWidth()
        ){ //button composable contains an other composable
            Text(text = stringResource(R.string.calculate))
        }


    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() { //used to display design
    CurrencyCalculatorTheme {
        Greeting("Android")
    }
}