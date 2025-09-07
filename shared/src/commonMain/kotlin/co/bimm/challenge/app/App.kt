package co.bimm.challenge.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import co.bimm.challenge.core.platform.PlatformActions
import co.bimm.challenge.sakeShops.presentation.sakeShopDetail.SakeShopDetailScreen
import co.bimm.challenge.sakeShops.presentation.sakeShopDetail.SakeShopDetailViewModel
import co.bimm.challenge.sakeShops.presentation.sakeShopList.SakeShopListScreen
import co.bimm.challenge.sakeShops.presentation.sakeShopList.SakeShopListViewModel
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val platformActions = koinInject<PlatformActions>()

        NavHost(
            navController = navController,
            startDestination = Route.SakeShopsGraph
        ) {
            navigation<Route.SakeShopsGraph>(
                startDestination = Route.SakeShopsList
            ) {
                composable<Route.SakeShopsList>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
                    val viewModel = koinViewModel<SakeShopListViewModel>()
                    val sakeShopDetailViewModel =
                        it.sharedKoinViewModel<SakeShopDetailViewModel>(navController)

                    SakeShopListScreen(
                        viewModel = viewModel,
                        onSakeShopTapped = { sakeShop ->
                            sakeShopDetailViewModel.onSelectSakeShop(sakeShop)
                            navController.navigate(
                                Route.SakeShopDetail(sakeShop.name)
                            )
                        },
                        onRetry = { viewModel.fetchSakeShops() }
                    )
                }
                composable<Route.SakeShopDetail>(
                    enterTransition = {
                        slideInHorizontally { initialOffset ->
                            initialOffset
                        }
                    },
                    exitTransition = {
                        slideOutHorizontally { initialOffset ->
                            initialOffset
                        }
                    }
                ) {
                    val sakeShopDetailViewModel =
                        it.sharedKoinViewModel<SakeShopDetailViewModel>(navController)

                    sakeShopDetailViewModel.selectedSakeShop.value?.let { sakeShop ->
                        SakeShopDetailScreen(
                            sakeShop = sakeShop,
                            onAddressClicked = { address ->
                                platformActions.openMap(address)
                            },
                            onWebsiteClicked = { website ->
                                platformActions.openWebsite(website)
                            },
                            onBackClicked = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}