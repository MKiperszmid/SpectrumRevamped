package com.mk.spectrumrevamped.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mk.core_ui.Gray
import com.mk.core_ui.LocalDimensions

@Composable
fun BottomNavigationBar(
    items: List<NavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onClick: (NavItem) -> Unit
) {
    val dimens = LocalDimensions.current
    val backstack = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primarySurface,
        elevation = dimens.extraSmall
    ) {
        items.forEach { item ->
            val selected = item.route == backstack.value?.destination?.route

            BottomNavigationItem(
                selected = selected, onClick = {
                    if (!selected) {
                        onClick(item)
                    }
                },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = Gray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = stringResource(id = item.name)
                        )
                        Text(
                            text = stringResource(id = item.name),
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp
                        )
                    }
                }
            )
        }
    }
}