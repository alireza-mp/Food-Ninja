package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.presentation.components.CustomTextField
import com.digimoplus.foodninja.presentation.components.TextFieldType
import com.digimoplus.foodninja.presentation.components.main_pages.OnBoardingMainPage
import com.digimoplus.foodninja.presentation.theme.AppTheme

/*@AndroidEntryPoint
class UserInformation : Fragment() {

    val viewModel: UserInformationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(isDark(isSystemInDarkTheme())) {
                    viewModel.name.value = arguments?.getString("name") ?: ""
                    UserInformationPage(navController = findNavController(), onBackPress = {
                        activity?.onBackPressed()
                    })
                }
            }
        }
    }
}*/

@Composable
fun UserInformationPage(navController: NavController, name: String/* onBackPress: () -> Unit*/) {

    val viewModel: UserInformationViewModel = hiltViewModel()
    viewModel.name.value = name
    val snackBarState = remember { SnackbarHostState() }
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

