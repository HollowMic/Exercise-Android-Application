package com.example.exerciseapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page(
    pageName: Int,
    openDrawer: (() -> Unit)? = null,
    navigate: (() -> Unit)? = null,
    actions: List<@Composable (() -> Unit)> = emptyList(),
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(pageName),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    if (openDrawer != null) {
                        IconButton(onClick = openDrawer) {
                            Icon(
                                Icons.Rounded.Menu,
                                contentDescription = stringResource(id = R.string.menu_icon_description),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    } else {
                        IconButton(onClick = { navigate?.invoke() }) {
                            Icon(
                                painter = painterResource(R.drawable.outline_arrow_back_24),
                                contentDescription = stringResource(id = R.string.back_button_description),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                },
                actions = {
                    actions.forEach {
                        it()
                    }
                }
            )
        }
    ) { innerPadding ->
        content(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}