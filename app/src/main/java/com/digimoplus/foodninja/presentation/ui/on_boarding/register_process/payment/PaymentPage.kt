package com.digimoplus.foodninja.presentation.ui.on_boarding.register_process.payment

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.PayButton
import com.digimoplus.foodninja.presentation.components.main_pages.OnBoardingMainPage
import com.digimoplus.foodninja.presentation.components.util.coloredShadow
import com.digimoplus.foodninja.presentation.theme.AppTheme

@Composable
fun PaymentPage(
    navController: NavController,
    name: String?,
    family: String?,
    phone: String?,
) {

    val viewModel: PaymentViewModel = hiltViewModel()

    OnBoardingMainPage(
        title = "Payment Method",
        description = "This data will be displayed in your account profile for security",
        navController = navController,
        onClick = {
            viewModel.savePaymentMethod(navController, name, family, phone)
        },
    ) {

        PayButton(
            modifier = Modifier.coloredShadow(
                offsetX = 8.dp,
                offsetY = 10.dp,
            ),
            id = if (AppTheme.colors.isLight) R.drawable.pay1_light else R.drawable.pay1_dark,
            isPressed = viewModel.isPress.value,
            name = "paypal"
        ) {
            viewModel.isPress.value = "paypal"
        }

        PayButton(
            modifier = Modifier.coloredShadow(
                offsetX = 8.dp,
                offsetY = 10.dp,
            ),
            id = if (AppTheme.colors.isLight) R.drawable.pay2_light else R.drawable.pay2_dark,
            isPressed = viewModel.isPress.value,
            name = "visa"
        ) {
            viewModel.isPress.value = "visa"
        }

        PayButton(
            modifier = Modifier.coloredShadow(
                offsetX = 8.dp,
                offsetY = 10.dp,
            ),
            id = if (AppTheme.colors.isLight) R.drawable.pay3_light else R.drawable.pay3_dark,
            isPressed = viewModel.isPress.value,
            name = "payoneer"
        ) {
            viewModel.isPress.value = "payoneer"
        }

    }

}