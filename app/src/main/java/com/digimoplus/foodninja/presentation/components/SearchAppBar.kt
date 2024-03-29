package com.digimoplus.foodninja.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.domain.util.HomePageState
import com.digimoplus.foodninja.presentation.components.util.animateAlpha
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.main.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SearchAppBar(
    viewModel: HomeViewModel,
    coroutineScope: CoroutineScope,
    listState: LazyListState?,
    searchQuery: (query: String) -> Unit,
    focusRequester: FocusRequester? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .animateAlpha(
                state = viewModel.launchAnimState,
                delayMillis = 300,
                durationMillis = 1000
            ),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        val f = remember {
            FocusRequester()
        }
        //val text by viewModel.searchValue.collectAsState()
        val focusManager = LocalFocusManager.current
        TextField(
            value = viewModel.search.value,
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester ?: f)
                .onFocusChanged {
                    if (it.isFocused && viewModel.pageState.value == HomePageState.DetailPage) {
                        viewModel.enableRestaurantFocus = true
                        viewModel.pageState.value = HomePageState.RestaurantPage
                    }
                },
            shape = RoundedCornerShape(15.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    tint = AppTheme.colors.secondary,
                    modifier = Modifier.size(30.dp)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = AppTheme.colors.secondaryText,
                disabledTextColor = Color.Transparent,
                backgroundColor = AppTheme.colors.onSecondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = AppTheme.colors.secondary
            ),
            textStyle = AppTheme.typography.body1,
            singleLine = true,
            placeholder = {
                Text(
                    text = "What do you want to order?",
                    color = AppTheme.colors.secondaryText,
                    modifier = Modifier.padding(vertical = 2.dp),
                    style = AppTheme.typography.body1
                )
            },
            onValueChange = {
                viewModel.search.value = it
            },
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (checkSearch(viewModel.search.value)) {
                        focusManager.clearFocus()
                        searchQuery(viewModel.search.value)
                    }
                }),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text
            ),
        )
        Spacer(modifier = Modifier.padding(end = 9.dp))
        Button(
            modifier = Modifier.size(55.dp),
            onClick = {
                coroutineScope.launch {
                    listState?.animateScrollToItem(0)
                    viewModel.pageState.value = HomePageState.SearchFilterPage
                }
            },
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 17.dp
            ),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.onSecondary),
            shape = RoundedCornerShape(15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filtter_light),
                tint = AppTheme.colors.secondary,
                modifier = Modifier.padding(vertical = 2.dp),
                contentDescription = ""
            )
        }

    }
}

private fun checkSearch(value: String): Boolean {
    return value.isNotBlank() && value.length >= 3
}
