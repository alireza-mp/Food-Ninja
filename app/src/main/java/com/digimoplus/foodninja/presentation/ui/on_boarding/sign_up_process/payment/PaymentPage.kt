package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.payment

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.model.UserInfo
import com.digimoplus.foodninja.presentation.components.main_pages.OnBoardingMainPage
import com.digimoplus.foodninja.presentation.components.PayButton
import com.digimoplus.foodninja.presentation.theme.AppTheme

/*@AndroidEntryPoint
class PaymentFragment : Fragment() {

    val viewModel: PaymentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {
                AppTheme(isDark(isSystemInDarkTheme())) {
                    PaymentPage(
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
fun PaymentPage(
    navController: NavController,
    userInfo: UserInfo?,
) {

    val viewModel: PaymentViewModel = hiltViewModel()

    OnBoardingMainPage(
        title = "Payment Method",
        description = "This data will be displayed in your account profile for security",
        navController = navController,
        onClick = {
            viewModel.savePaymentMethod(navController, userInfo)
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