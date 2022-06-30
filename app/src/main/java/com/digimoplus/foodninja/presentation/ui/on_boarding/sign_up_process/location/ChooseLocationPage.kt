package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.location

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.UserInfo
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.presentation.components.CircleBallProgress
import com.digimoplus.foodninja.presentation.components.main_pages.OnBoardingMainPage
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.isDark
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/*@AndroidEntryPoint
class ChooseLocationFragment : Fragment() {
    private val viewModel: ChooseLocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(isDark(isSystemInDarkTheme())) {
                    ChooseLocationPage(
                        viewModel = viewModel,
                        navController = findNavController(),
                        onBackPress = {
                            activity?.onBackPressed()
                        },
                        bundle = requireArguments()
                    )
                }
            }
        }
    }
}*/

@Composable
fun ChooseLocationPage(
    navController: NavController,
    userInfo: UserInfo?,
) {
    val viewModel: ChooseLocationViewModel = hiltViewModel()
    val defaultLocation = LatLng(34.64, 50.88)
    val coroutineScope = rememberCoroutineScope()

    OnBoardingMainPage(
        title = "Set Your Location ",
        description = "This data will be displayed in your account profile for security",
        snackBarState = viewModel.snackBarState,
        buttonTitle = if (viewModel.mapIsVisible.value) "Save" else "Next",
        loading = viewModel.loading.value,
        navController = navController,
        onClick = {
            if (viewModel.mapIsVisible.value) {
                viewModel.saveLocation(userInfo, navController)

            } else {
                if (viewModel.selectedLocation.value == defaultLocation) {
                    Log.i(TAG, "ChooseLocationPage: DefaultLocation")
                    coroutineScope.launch {
                        viewModel.snackBarState.showSnackbar("please choose a location")
                    }
                }
            }
        }) {

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
        Column(modifier = Modifier.padding(top = AppTheme.dimensions.grid_2)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier.padding(start = AppTheme.dimensions.grid_1),
                    painter = painterResource(id = R.drawable.location_pin),
                    contentDescription = "",
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = AppTheme.dimensions.grid_1),
                    text = "Your Location",
                    color = AppTheme.colors.titleText,
                    style = AppTheme.typography.h7
                )
            }
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_2_5))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = AppTheme.dimensions.grid_1,
                        end = AppTheme.dimensions.grid_1,
                        bottom = AppTheme.dimensions.grid_1
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
                        modifier = Modifier.padding(vertical = AppTheme.dimensions.grid_1_5),
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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        contentAlignment = Alignment.Center,
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
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
        if (viewModel.loading.value) {
            Box(modifier = Modifier
                .matchParentSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() } // This is mandatory
                ) { }, contentAlignment = Alignment.Center
            ) {
                CircleBallProgress()
            }
        }
    }

}