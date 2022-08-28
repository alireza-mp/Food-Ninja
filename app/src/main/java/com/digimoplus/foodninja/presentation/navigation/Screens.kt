package com.digimoplus.foodninja.presentation.navigation

sealed class Screens(val route: String) {

    object Splash : Screens("splash")
    object Introduction : Screens("introduction")
    object SignIn : Screens("signIn")
    object SignUp : Screens("signUp")
    object Main : Screens("main")
    object ForgetPassword : Screens("forget_password")
    object Payment : Screens("payment/{user}")
    object UploadProfile : Screens("upload_Profile/{user}")
    object ChooseLocation : Screens("choose_location")
    object SuccessPage : Screens("success_page")
    object BasketPage : Screens("basket_page")
    object ChatDetail : Screens("chat_detail")

    object RestaurantDetail : Screens("restaurant_detail/{id}") {
        fun createIdRoute(id: Int) = "restaurant_detail/$id"
    }

    object MenuDetail : Screens("menu_detail/{id}") {
        fun createIdRoute(id: Int) = "menu_detail/$id"
    }

    object UserInformation : Screens("user_information?name={name}") {
        fun createRoute(name: String) = "user_information?name=$name"
    }
}