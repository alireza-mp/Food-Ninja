package com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.util.UiState
import com.digimoplus.foodninja.presentation.components.BallProgress
import com.digimoplus.foodninja.presentation.components.main_pages.OnBoardingMainPage
import com.digimoplus.foodninja.presentation.components.util.dps
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

// name & family & phone is user information that save to server
@Composable
fun ChooseLocationPage(
    navController: NavController,
    name: String?,
    family: String?,
    phone: String?,
) {
    val viewModel: ChooseLocationViewModel = hiltViewModel()
    // default location in qom
    val defaultLocation = LatLng(34.64, 50.88)
    val coroutineScope = rememberCoroutineScope()

    OnBoardingMainPage(
        title = "Set Your Location ",
        description = "This data will be displayed in your account profile for security",
        snackBarState = viewModel.snackBarState,
        buttonTitle = if (viewModel.mapIsVisible.value) "Save" else "Next",
        loading = viewModel.uiState == UiState.Loading,
        navController = navController,
        onClick = {
            // on button clicked
            // is user choose the location
            if (viewModel.mapIsVisible.value) {
                // go to next page
                viewModel.saveLocation(name, family, phone, navController)
            } else {
                // show map
                if (viewModel.selectedLocation.value == defaultLocation) {
                    coroutineScope.launch {
                        viewModel.snackBarState.showSnackbar("please choose a location")
                    }
                }
            }
        },
    ) {

        // map visibility
        if (viewModel.mapIsVisible.value) {
            ShowMap(viewModel, defaultLocation)
        } else {
            ChooseLocation(viewModel)
        }

    }
}

@Composable
fun ChooseLocation(viewModel: ChooseLocationViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = AppTheme.colors.surface
    ) {
        Column(modifier = Modifier.padding(top = 4.dps)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier.padding(start = 2.dps),
                    painter = painterResource(id = R.drawable.location_pin),
                    contentDescription = "",
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 2.dps),
                    text = "Your Location",
                    color = AppTheme.colors.titleText,
                    style = AppTheme.typography.h7
                )
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 2.dps,
                        end = 2.dps,
                        bottom = 2.dps
                    ),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.primary),
                onClick = {
                    viewModel.mapIsVisible.value = true
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 4.dps),
                        text = "Set Location",
                        style = AppTheme.typography.h7,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ShowMap(viewModel: ChooseLocationViewModel, defaultLocation: LatLng) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 12f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dps),
        cameraPositionState = cameraPositionState,
        onMapClick = {
            viewModel.selectedLocation.value = it
        }
    ) {
        Marker(
            visible = viewModel.selectedLocation.value != defaultLocation,
            position = viewModel.selectedLocation.value
        )
    }
    if (viewModel.uiState == UiState.Loading || !viewModel.mapIsVisible.value) {
        Box(
            modifier = Modifier
                .padding(top = 10.dps)
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() } // This is mandatory
                ) { },
            contentAlignment = Alignment.Center,
        ) {
            BallProgress()
        }
    }


}