package com.wafflestudio.assignment5

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Tutorial(
    modifier: Modifier = Modifier
){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if(shouldShowOnboarding){
            OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
        }else{
            Greetings()
        }
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit
){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Welcome!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ){
            Text("Continue")
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) {"$it"}
){
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names){
                name -> Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        CardContent(name)
    }
}

@Composable
private fun CardContent(name:String){
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ){
        Column(modifier = Modifier
            .weight(1f)
            .padding(12.dp)
        ){
            Text(text = "Hello")
            Text(text = name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            if(expanded){
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = {expanded = !expanded}) {
            Icon(
                imageVector = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = if(expanded){
                    stringResource(id = R.string.show_less)
                }else {
                    stringResource(id = R.string.show_more)
                }
            )
        }
    }

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        Row(modifier = Modifier.padding(24.dp)){

            ElevatedButton(
                onClick = { expanded = !expanded}
            ) {
                Text(if(expanded) "Show less" else "Show more")
            }
        }

    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview(){
    OnboardingScreen(onContinueClicked = {})
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "dark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
private fun GreetingPreview(){
    Greetings()
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun MtAppPreview(){
    Tutorial()
}