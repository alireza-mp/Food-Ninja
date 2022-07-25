@file:OptIn(ExperimentalComposeUiApi::class)

package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.presentation.components.NoneTextField
import com.digimoplus.foodninja.presentation.components.main_pages.OnBoardingMainPage
import com.digimoplus.foodninja.presentation.components.util.dps

@Composable
fun UserInformationPage(navController: NavController, name: String/* onBackPress: () -> Unit*/) {

    val viewModel: UserInformationViewModel = hiltViewModel()
    viewModel.name.value = name
    val snackBarState = remember { SnackbarHostState() }
    val (nameFocus, familyFocus, phoneFocus) = remember { FocusRequester.createRefs() }
    val focusManager = LocalFocusManager.current

    OnBoardingMainPage(
        title = "Fill in your bio to get started",
        description = "This data will be displayed in your account profile for security",
        snackBarState = snackBarState,
        loading = viewModel.loading.value,
        navController = navController,
        onClick = {
            viewModel.addInfo(snackBarHost = snackBarState, navController = navController)
            //navController.navigate(R.id.action_userInformationFragment_to_paymentFragment)
        }
    ) {
        NoneTextField(
            placeHolder = "First Name",
            value = viewModel.name,
            focusRequester = nameFocus,
            nextFocusRequester = familyFocus,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        )
        Spacer(modifier = Modifier.padding(top = 8.dps))
        NoneTextField(
            placeHolder = "Last Name",
            value = viewModel.family,
            focusRequester = familyFocus,
            nextFocusRequester = phoneFocus,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        )
        Spacer(modifier = Modifier.padding(top = 8.dps))
        NoneTextField(
            placeHolder = "Mobile Number",
            value = viewModel.phone,
            focusRequester = phoneFocus,
            nextFocusRequester = FocusRequester(),
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done,
            onFocusDown = {
                focusManager.clearFocus()
                viewModel.addInfo(snackBarHost = snackBarState, navController = navController)
            }
        )
    }
}

