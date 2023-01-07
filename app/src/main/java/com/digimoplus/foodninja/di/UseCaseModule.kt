package com.digimoplus.foodninja.di

import com.digimoplus.foodninja.domain.repository.*
import com.digimoplus.foodninja.domain.useCase.basket.*
import com.digimoplus.foodninja.domain.useCase.chat.*
import com.digimoplus.foodninja.domain.useCase.home_detail.HomeDetailUseCase
import com.digimoplus.foodninja.domain.useCase.login.CheckAuthenticationUseCase
import com.digimoplus.foodninja.domain.useCase.login.LoginUseCase
import com.digimoplus.foodninja.domain.useCase.menus.GetMenusListUseCase
import com.digimoplus.foodninja.domain.useCase.menus.GetPopularMenusListUseCase
import com.digimoplus.foodninja.domain.useCase.menus.SearchMenuUseCase
import com.digimoplus.foodninja.domain.useCase.profile.CheckProfileImageUrlUseCase
import com.digimoplus.foodninja.domain.useCase.register.*
import com.digimoplus.foodninja.domain.useCase.restaurants.GetNearestRestaurantsListUseCase
import com.digimoplus.foodninja.domain.useCase.restaurants.GetRestaurantDetailsUseCase
import com.digimoplus.foodninja.domain.useCase.restaurants.GetRestaurantsListUseCase
import com.digimoplus.foodninja.domain.useCase.restaurants.SearchRestaurantUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetNearestRestaurantListUseCase(
        restaurantRepository: RestaurantRepository,
    ): GetNearestRestaurantsListUseCase = GetNearestRestaurantsListUseCase(
        restaurantRepository = restaurantRepository
    )

    @Provides
    fun provideGetPopularMenusListUseCase(
        menuRepository: MenuRepository,
    ): GetPopularMenusListUseCase = GetPopularMenusListUseCase(
        menuRepository = menuRepository
    )

    @Provides
    fun provideHomeDetailUseCase(
        getPopularMenusListUseCase: GetPopularMenusListUseCase,
        getNearestRestaurantsListUseCase: GetNearestRestaurantsListUseCase,
    ): HomeDetailUseCase = HomeDetailUseCase(
        getNearestRestaurantsListUseCase = getNearestRestaurantsListUseCase,
        getPopularMenusListUseCase = getPopularMenusListUseCase,
    )

    @Provides
    fun provideGetRestaurantsListUseCase(
        restaurantRepository: RestaurantRepository,
    ): GetRestaurantsListUseCase = GetRestaurantsListUseCase(
        restaurantRepository = restaurantRepository,
    )

    @Provides
    fun provideSearchRestaurantUseCase(
        restaurantRepository: RestaurantRepository,
    ): SearchRestaurantUseCase = SearchRestaurantUseCase(
        restaurantRepository = restaurantRepository,
    )

    @Provides
    fun provideGetMenusListUseCase(
        menuRepository: MenuRepository,
    ): GetMenusListUseCase = GetMenusListUseCase(
        menuRepository = menuRepository,
    )

    @Provides
    fun provideMenusListUseCase(
        menuRepository: MenuRepository,
    ): SearchMenuUseCase = SearchMenuUseCase(
        menuRepository = menuRepository,
    )

    @Provides
    fun provideGetRestaurantDetailsUseCase(
        restaurantRepository: RestaurantRepository,
    ): GetRestaurantDetailsUseCase = GetRestaurantDetailsUseCase(
        restaurantRepository = restaurantRepository,
    )

    @Provides
    fun provideBasketUseCases(
        getBasketListUseCase: GetBasketListUseCase,
        updateBasketItemCountUseCase: UpdateBasketItemCountUseCase,
        deleteBasketItemUseCase: DeleteBasketItemUseCase,
        getBasketItemCountUseCase: GetBasketItemCountUseCase,
        deleteCurrentRestaurantUseCase: DeleteCurrentRestaurantUseCase,
        getCurrentRestaurantUseCase: GetCurrentRestaurantUseCase,
        addNewItemToBasketUseCase: AddNewItemToBasketUseCase,
    ): BasketUseCases = BasketUseCases(
        getBasketListUseCase = getBasketListUseCase,
        updateBasketItemCountUseCase = updateBasketItemCountUseCase,
        deleteBasketItemUseCase = deleteBasketItemUseCase,
        getBasketItemCountUseCase = getBasketItemCountUseCase,
        deleteCurrentRestaurantUseCase = deleteCurrentRestaurantUseCase,
        getCurrentRestaurantUseCase = getCurrentRestaurantUseCase,
        addNewItemToBasketUseCase = addNewItemToBasketUseCase,
    )

    @Provides
    fun provideGetBasketListUseCase(
        basketRepository: BasketRepository,
    ): GetBasketListUseCase {
        return GetBasketListUseCase(basketRepository = basketRepository)
    }

    @Provides
    fun provideDeleteBasketItemUseCase(
        basketRepository: BasketRepository,
    ): DeleteBasketItemUseCase = DeleteBasketItemUseCase(
        basketRepository = basketRepository
    )


    @Provides
    fun provideUpdateBasketItemCountUseCase(
        basketRepository: BasketRepository,
    ): UpdateBasketItemCountUseCase = UpdateBasketItemCountUseCase(
        basketRepository = basketRepository
    )

    @Provides
    fun provideGetBasketItemCountUseCase(
        basketRepository: BasketRepository,
    ): GetBasketItemCountUseCase {
        return GetBasketItemCountUseCase(basketRepository = basketRepository)
    }

    @Provides
    fun provideDeleteCurrentRestaurantUseCase(
        basketRepository: BasketRepository,
    ): DeleteCurrentRestaurantUseCase {
        return DeleteCurrentRestaurantUseCase(basketRepository = basketRepository)
    }

    @Provides
    fun provideGetCurrentRestaurantUseCase(
        basketRepository: BasketRepository,
    ): GetCurrentRestaurantUseCase {
        return GetCurrentRestaurantUseCase(basketRepository = basketRepository)
    }

    @Provides
    fun provideAddNewItemToBasketUseCase(
        basketRepository: BasketRepository,
    ): AddNewItemToBasketUseCase {
        return AddNewItemToBasketUseCase(basketRepository = basketRepository)
    }

    @Provides
    fun provideCheckAuthenticationUseCase(
        loginRepository: LoginRepository,
    ): CheckAuthenticationUseCase {
        return CheckAuthenticationUseCase(loginRepository)
    }

    @Provides
    fun provideLoginUseCase(
        loginRepository: LoginRepository,
    ): LoginUseCase {
        return LoginUseCase(loginRepository)
    }

    @Provides
    fun provideCheckCompleteRegisterUseCase(
        registerRepository: RegisterRepository,
    ): CheckCompleteRegisterUseCase {
        return CheckCompleteRegisterUseCase(registerRepository)
    }

    @Provides
    fun provideCheckIntroductionUseCase(
        registerRepository: RegisterRepository,
    ): CheckIntroductionUseCase {
        return CheckIntroductionUseCase(registerRepository)
    }

    @Provides
    fun provideCheckProfileImageUrlUseCase(
        profileRepository: ProfileRepository,
    ): CheckProfileImageUrlUseCase {
        return CheckProfileImageUrlUseCase(profileRepository)
    }

    @Provides
    fun provideCompleteRegisterUseCase(
        registerRepository: RegisterRepository,
    ): CompleteRegisterUseCase {
        return CompleteRegisterUseCase(registerRepository)
    }

    @Provides
    fun provideRegisterUseCase(
        registerRepository: RegisterRepository,
    ): RegisterUseCase {
        return RegisterUseCase(registerRepository)
    }

    @Provides
    fun provideSaveIntroductionUseCase(
        registerRepository: RegisterRepository,
    ): SaveIntroductionUseCase {
        return SaveIntroductionUseCase(registerRepository)
    }

    @Provides
    fun provideSavePaymentMethodUseCase(
        registerRepository: RegisterRepository,
    ): SavePaymentMethodUseCase {
        return SavePaymentMethodUseCase(registerRepository)
    }

    @Provides
    fun provideUploadProfileImageUseCase(
        registerRepository: RegisterRepository,
    ): UploadProfileImageUseCase {
        return UploadProfileImageUseCase(registerRepository)
    }

    @Provides
    fun provideGetBasketItemsCountUseCase(
        basketRepository: BasketRepository,
    ): GetBasketItemsCountUseCase {
        return GetBasketItemsCountUseCase(basketRepository)
    }

    @Provides
    fun provideDisconnectChatUseCase(
        chatRepository: ChatRepository,
    ): DisconnectChatUseCase {
        return DisconnectChatUseCase(chatRepository)
    }

    @Provides
    fun provideListeningToMessagesUseCase(
        chatRepository: ChatRepository,
    ): ListeningToMessagesUseCase {
        return ListeningToMessagesUseCase(chatRepository)
    }

    @Provides
    fun provideListeningToTypeIngUseCase(
        chatRepository: ChatRepository,
    ): ListeningToTypeIngUseCase {
        return ListeningToTypeIngUseCase(chatRepository)
    }

    @Provides
    fun provideSendMessageUseCase(
        chatRepository: ChatRepository,
    ): SendMessageUseCase {
        return SendMessageUseCase(chatRepository)
    }

    @Provides
    fun provideStartTypeIngUseCase(
        chatRepository: ChatRepository,
    ): StartTypeIngUseCase {
        return StartTypeIngUseCase(chatRepository)
    }

    @Provides
    fun provideStopTypeIngUseCase(
        chatRepository: ChatRepository,
    ): StopTypeIngUseCase {
        return StopTypeIngUseCase(chatRepository)
    }

    @Provides
    fun provideChatUseCases(
        disconnectChatUseCase: DisconnectChatUseCase,
        listeningToMessagesUseCase: ListeningToMessagesUseCase,
        listeningToTypeIngUseCase: ListeningToTypeIngUseCase,
        sendMessagesUseCase: SendMessageUseCase,
        startTypeIngUseCase: StartTypeIngUseCase,
        stopTypeIngUseCase: StopTypeIngUseCase,
    ): ChatUseCases {
        return ChatUseCases(
            disconnectChatUseCase = disconnectChatUseCase,
            listeningToMessagesUseCase = listeningToMessagesUseCase,
            listeningToTypeIngUseCase = listeningToTypeIngUseCase,
            sendMessagesUseCase = sendMessagesUseCase,
            startTypeIngUseCase = startTypeIngUseCase,
            stopTypeIngUseCase = stopTypeIngUseCase,
        )
    }

}