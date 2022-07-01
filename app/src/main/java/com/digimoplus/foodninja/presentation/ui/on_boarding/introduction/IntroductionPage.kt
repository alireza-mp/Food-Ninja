package com.digimoplus.foodninja.presentation.ui.on_boarding.introduction

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.theme.getDimensions
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


/*@ExperimentalPagerApi
@AndroidEntryPoint
class IntroductionFragment : Fragment() {

    private val viewModel: IntroductionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(isDark(isSystemInDarkTheme())) {
                    IntroductionPage(viewModel, findNavController())
                }
            }
        }
    }
}*/

@ExperimentalPagerApi
@Composable
fun IntroductionPage(
    navController: NavController
) {
    val viewModel :IntroductionViewModel  = hiltViewModel()
    val dimensions = getDimensions(LocalConfiguration.current.screenHeightDp)
    val pagerState = rememberPagerState(pageCount = 2)

    HorizontalPager(
        state = pagerState,
        itemSpacing = 20.dp,
        modifier = Modifier.background(color = AppTheme.colors.background)
    ) { index ->
        when (index) {
            0 -> IntroductionPageOne(pagerState = pagerState, dimensions = dimensions)
            1 -> IntroductionPageTwo(
                viewModel = viewModel,
                navController = navController,
                dimensions = dimensions
            )
        }
    }
}