package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.util.Constants.Companion.TAG
import com.digimoplus.foodninja.presentation.components.*
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.buttonGradient
import com.digimoplus.foodninja.presentation.theme.isDark
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserInformation : Fragment() {

    val viewModel: UserInformationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(isDark(isSystemInDarkTheme())) {
                    viewModel.name.value = arguments?.getString("name") ?: ""
                    UserInformationPage(navController = findNavController())
                }
            }
        }
    }


    @Composable
    fun UserInformationPage(navController: NavController) {
        val snackBarState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()
        OnBoardingDisplay(
            title = "Fill in your bio to get started",
            description = "This data will be displayed in your account profile for security",
            snackBarState = snackBarState,
            loading = viewModel.loading.value,
            onBackPress = {

            },
            onClick = {
                coroutineScope.launch {
                    viewModel.addInfo(snackBarHost = snackBarState, navController = navController)
                }
            }
        ) {
            CustomTextField(
                placeHolder = "First Name",
                value = viewModel.name,
                textFieldType = TextFieldType.None
            )
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_3))
            CustomTextField(
                placeHolder = "Last Name",
                value = viewModel.family,
                textFieldType = TextFieldType.None
            )
            Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_3))
            CustomTextField(
                placeHolder = "Mobile Number",
                value = viewModel.phone,
                textFieldType = TextFieldType.None
            )
        }
    }

}