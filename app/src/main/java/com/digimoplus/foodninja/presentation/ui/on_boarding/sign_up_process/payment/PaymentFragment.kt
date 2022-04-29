package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.components.DisplayBackgroundImage
import com.digimoplus.foodninja.presentation.components.OnBoardingDisplay
import com.digimoplus.foodninja.presentation.components.PayButton
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.isDark
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    val viewModel: PaymentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(isDark(isSystemInDarkTheme())) {
                    PaymentPage(
                        viewModel = viewModel,
                        navController = findNavController(),
                        onBackPress = {
                            activity?.onBackPressed()
                        })
                }
            }
        }
    }
}

@Composable
fun PaymentPage(
    viewModel: PaymentViewModel,
    navController: NavController,
    onBackPress: () -> Unit
) {

    OnBoardingDisplay(
        title = "Payment Method",
        description = "This data will be displayed in your account profile for security",
        onBackPress = {
            onBackPress()
        },
        onClick = {
            viewModel.savePaymentMethod(navController)
        },
    ) {
        PayButton(
            id = if (AppTheme.colors.isLight) R.drawable.pay1_light else R.drawable.pay1_dark,
            isPressed = viewModel.isPress.value,
            name = "paypal"
        ) {
            viewModel.isPress.value = "paypal"
        }
        Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_1_5))
        PayButton(
            id = if (AppTheme.colors.isLight) R.drawable.pay2_light else R.drawable.pay2_dark,
            isPressed = viewModel.isPress.value,
            name = "visa"
        ) {
            viewModel.isPress.value = "visa"
        }
        Spacer(modifier = Modifier.padding(top = AppTheme.dimensions.grid_1_5))
        PayButton(
            id = if (AppTheme.colors.isLight) R.drawable.pay3_light else R.drawable.pay3_dark,
            isPressed = viewModel.isPress.value,
            name = "payoneer"
        ) {
            viewModel.isPress.value = "payoneer"
        }

    }


}