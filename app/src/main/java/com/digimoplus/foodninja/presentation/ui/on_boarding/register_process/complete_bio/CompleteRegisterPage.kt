@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeUiApi::class)

package com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.complete_bio

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.presentation.components.NoneTextField
import com.digimoplus.foodninja.presentation.components.main_pages.OnBoardingMainPage
import com.digimoplus.foodninja.presentation.components.util.coloredShadow

@Composable
fun CompleteRegisterPage(navController: NavController, name: String) {

    val viewModel: CompleteRegisterViewModel = hiltViewModel()
    //set name from last page
    viewModel.name.value = name
    // focus request references
    val (nameFocus, familyFocus, phoneFocus) = remember { FocusRequester.createRefs() }
    //focus manager
    val focusManager = LocalFocusManager.current

    OnBoardingMainPage(
        title = "Fill in your bio to get started",
        description = "This data will be displayed in your account profile for security",
        snackBarState = viewModel.snackBarState,
        loading = false,
        navController = navController,
        backButtonEnabled = false,
        onClick = {
            viewModel.navigateToPaymentPage(navController = navController)
        },
    ) {
        NoneTextField(
            modifier = Modifier.coloredShadow(
                offsetX = 8.dp,
                offsetY = 10.dp,
            ),
            placeHolder = "First Name",
            value = viewModel.name,
            focusRequester = nameFocus,
            nextFocusRequester = familyFocus,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        )

        Spacer(modifier = Modifier.padding(top = 20.dp))

        NoneTextField(
            modifier = Modifier.coloredShadow(
                offsetX = 8.dp,
                offsetY = 10.dp,
            ),
            placeHolder = "Last Name",
            value = viewModel.family,
            focusRequester = familyFocus,
            nextFocusRequester = phoneFocus,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        )

        Spacer(modifier = Modifier.padding(top = 20.dp))

        NoneTextField(
            modifier = Modifier.coloredShadow(
                offsetX = 8.dp,
                offsetY = 10.dp,
            ),
            placeHolder = "Mobile Number",
            value = viewModel.phone,
            focusRequester = phoneFocus,
            nextFocusRequester = FocusRequester(),
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done,
            onFocusDown = {
                focusManager.clearFocus()
                viewModel.navigateToPaymentPage(navController = navController)
            },
        )
    }
}

