package com.example.missioncontrolcenterinterface

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.missioncontrolcenterinterface.ui.theme.MissionControlCenterInterfaceTheme
import com.example.missioncontrolcenterinterface.vm.StationViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MissionControlCenterInterfaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    StationState()
                }
            }
        }
    }
}


@Composable
@Preview
fun StationState() {
//    Box(
//        modifier = with (Modifier){
//            fillMaxSize()
//                .paint(
//                    // Replace with your image id
//        }
//    )
    val viewModel: StationViewModel = viewModel()
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        scope.launch {
            while (true) {
                viewModel.getStation()
                delay(2000)
            }
        }
    }
    val station by viewModel.stationUiState.collectAsState()
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        Text(text = station.name, color = Color.White)
        Text(text = station.batteryLevel.toString(), color = Color.White)
        Text(
            text = station.transform.toString(),
            Modifier.padding(bottom = 400.dp),
            color = Color.White
        )
        if ((station?.transform?.get("requiredLinearSpeed") ?: 0.0) < 1)
            AsyncImage(
                model = "https://cdn.discordapp.com/attachments/909504736036794409/1167859561076625469/ship.png?ex=654fa8f9&is=653d33f9&hm=7bbaca37f301dffad4705273e3c997f23c4eda013f0a185f7a12176fa66465cd&",
                contentDescription = null,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .size(120.dp)
            )
        else {
            AsyncImage(
                model = "https://cdn.discordapp.com/attachments/909504736036794409/1167859561076625469/ship.png?ex=654fa8f9&is=653d33f9&hm=7bbaca37f301dffad4705273e3c997f23c4eda013f0a185f7a12176fa66465cd&",
                contentDescription = null,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .size(120.dp)
            )
        }
        Row {
            var isActive by remember {
                mutableStateOf(true)
            }
            FloatingActionButton(
                onClick = {
                    if (isActive) scope.launch {
                        isActive = false
                        delay(2000)
                        isActive = true
                        viewModel.targetRotationSpeed -= 10
                        viewModel.changeRotationSpeed(viewModel.targetRotationSpeed)
                    }
                },
                modifier = Modifier.padding(start = 20.dp),
                containerColor = if (isActive) {
                    Color(android.graphics.Color.parseColor("#4BB34B"))
                } else {
                    Color(android.graphics.Color.parseColor("#E64646"))
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = null,
                )
            }
            FloatingActionButton(
                onClick = {
                    if (isActive) scope.launch {
                        isActive = false
                        delay(2000)
                        isActive = true
                        viewModel.targerLinearSpeed += 10
                        viewModel.changeLinearSpeed(viewModel.targerLinearSpeed)
                    }
                },
                modifier = Modifier.padding(start = 60.dp),
                containerColor = if (isActive) {
                    Color(android.graphics.Color.parseColor("#4BB34B"))
                } else {
                    Color(android.graphics.Color.parseColor("#E64646"))
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_local_fire_department_24),
                    contentDescription = null,
                )
            }
            FloatingActionButton(
                onClick = {
                    if (isActive) scope.launch {
                        isActive = false
                        delay(2000)
                        isActive = true
                        if (viewModel.targerLinearSpeed > 0) {
                            viewModel.targerLinearSpeed -= 10
                            viewModel.changeLinearSpeed(viewModel.targerLinearSpeed)
                        }
                    }
                },
                modifier = Modifier.padding(start = 10.dp),
                containerColor = if (isActive) {
                    Color(android.graphics.Color.parseColor("#4BB34B"))
                } else {
                    Color(android.graphics.Color.parseColor("#E64646"))
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_anchor_24),
                    contentDescription = null,
                )
            }
            FloatingActionButton(
                onClick = {
                    if (isActive)
                        scope.launch {
                            isActive = false
                            delay(2000)
                            viewModel.targetRotationSpeed += 10
                            viewModel.changeRotationSpeed(viewModel.targetRotationSpeed)
                            isActive = true
                        }
                },
                modifier = Modifier.padding(start = 60.dp, bottom = 25.dp),
                containerColor = if (isActive) {
                    Color(android.graphics.Color.parseColor("#4BB34B"))
                } else {
                    Color(android.graphics.Color.parseColor("#E64646"))
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                    contentDescription = null,
                )
            }
        }
    }

}