@file:OptIn(ExperimentalPermissionsApi::class)

package com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.upload_profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digimoplus.foodninja.R
import com.digimoplus.foodninja.presentation.Screens
import com.digimoplus.foodninja.presentation.components.BallProgress
import com.digimoplus.foodninja.presentation.components.CardUploadPhoto
import com.digimoplus.foodninja.presentation.components.main_pages.OnBoardingMainPage
import com.digimoplus.foodninja.presentation.components.util.dps
import com.digimoplus.foodninja.presentation.theme.AppTheme
import com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio.USER_INFO_FAMILY
import com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio.USER_INFO_NAME
import com.digimoplus.foodninja.presentation.ui.on_boarding.sign_up_process.complete_bio.USER_INFO_PHONE
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

// name & family & phone is user information that sent to next page

@Composable
fun UploadProfilePage(
    navController: NavController,
    name: String?,
    family: String?,
    phone: String?,
) {

    val viewModel: UploadPhotoViewModel = hiltViewModel()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // gallery result
    val resultGallery =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let {
                    viewModel.uploadProfile(context, it)
                }
            } else {
                coroutineScope.launch {
                    viewModel.snackBarState.showSnackbar("Error Please try again")
                }
            }
        }

    // camera result
    val resultCamera =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val bitmap = result.data?.extras?.get("data") as Bitmap
                viewModel.uploadProfile(bitmap)
            } else {
                coroutineScope.launch {
                    viewModel.snackBarState.showSnackbar("Error Please try again")
                }
            }
        }


    OnBoardingMainPage(
        title = "Upload Your Photo Profile",
        description = "This data will be displayed in your account profile for security",
        snackBarState = viewModel.snackBarState,
        navController = navController,
        loading = viewModel.loading.value,
        onClick = {
            // on button click
            if (viewModel.imageUrl.value == "none") {
                coroutineScope.launch {
                    viewModel.snackBarState.showSnackbar("please set your profile image")
                }
            } else {
                // send user info to choose location page
                navController.currentBackStackEntry?.arguments?.putString(USER_INFO_NAME, name)
                navController.currentBackStackEntry?.arguments?.putString(USER_INFO_FAMILY, family)
                navController.currentBackStackEntry?.arguments?.putString(USER_INFO_PHONE, phone)
                navController.navigate(Screens.ChooseLocation.route)
            }
        }) {

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {

            // check photo is set or not
            if (viewModel.imageUrl.value != "none") {
                ShowProfilePhoto(viewModel)
            } else {
                ChoseProfilePhoto(
                    viewModel = viewModel,
                    resultGallery = resultGallery,
                    resultCamera = resultCamera
                )
            }
            if (viewModel.loading.value) {
                BallProgress(
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }

        }
    }
}

@Composable
private fun ShowProfilePhoto(viewModel: UploadPhotoViewModel) {
    Card(
        shape = RoundedCornerShape(25.dp), elevation = 0.dp, modifier = Modifier
            .size(250.dp)
            .padding(top = 6.dps)
    ) {
        GlideImage(
            imageModel = viewModel.imageUrl.value,
            loading = {
                Box(modifier = Modifier.matchParentSize()) {
                    BallProgress(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            failure = {
                Box(modifier = Modifier.matchParentSize()) {
                    Text(
                        text = "image request failed", style = AppTheme.typography.body,
                        color = AppTheme.colors.titleText
                    )
                }
            })
    }

}

@ExperimentalPermissionsApi
@Composable
private fun ChoseProfilePhoto(
    viewModel: UploadPhotoViewModel,
    resultGallery: ActivityResultLauncher<Intent>,
    resultCamera: ActivityResultLauncher<Intent>,
) {

    val cameraPermissions = rememberPermissionState(
        Manifest.permission.CAMERA
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CardUploadPhoto(
            title = "From Gallery",
            id = R.drawable.gallery
        ) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            resultGallery.launch(intent)
        }
        Spacer(modifier = Modifier.padding(top = 6.dps))
        CardUploadPhoto(
            title = "Take Photo",
            id = R.drawable.camera
        ) {
            cameraPermissions.launchPermissionRequest()
        }
        if (cameraPermissions.status == PermissionStatus.Granted) {
            if (!viewModel.loading.value) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                resultCamera.launch(intent)
            }
        }
    }

}

