package com.words.cards.android.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.words.cards.presentation.event.SettingsEvent
import com.words.cards.presentation.intent.SettingsIntent
import com.words.cards.presentation.state.ButtonState
import com.words.cards.presentation.state.Language
import com.words.cards.presentation.state.SettingsScreenContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun LanguageSettingsScreen(
    modifier: Modifier = Modifier,
    onNextClicked: () -> Unit,
) {
    val viewModel = koinViewModel<SettingsViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.onIntent(SettingsIntent.InitialLoad)
    }

    LaunchedEffect(state.event) {
        when (val event = state.event) {
            SettingsEvent.Empty -> Unit
            SettingsEvent.OnNextClicked -> onNextClicked()
        }
    }

    SettingsPane(
        modifier = modifier,
        content = state.content,
        onLanguageSelected = { isPrimary, language ->
            viewModel.onIntent(SettingsIntent.SelectLanguage(isPrimary, language))
        },
        onNextClicked = {
            viewModel.onIntent(SettingsIntent.OnNextClicked)
        }
    )
}

@Composable
fun SettingsPane(
    modifier: Modifier = Modifier,
    content: SettingsScreenContent,
    onLanguageSelected: (Boolean, Language.Selected) -> Unit,
    onNextClicked: () -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var isPrimarySelected by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // Title
            Text(
                text = content.title,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = content.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Primary language
            LanguageSection(
                description = content.primaryDescription,
                language = content.primary,
                onClick = {
                    isPrimarySelected = true
                    showBottomSheet = true
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Secondary language
            LanguageSection(
                description = content.secondaryDescription,
                language = content.secondary,
                onClick = {
                    isPrimarySelected = false
                    showBottomSheet = true
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onNextClicked() },
                enabled = content.buttonState.active,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = content.buttonState.text)
            }
        }

        if (showBottomSheet) {
            CountryPickerBottomSheet(
                languageList = content.languages,
                onCountrySelected = { selected ->
                    onLanguageSelected(isPrimarySelected, selected)
                    showBottomSheet = false
                },
                onDismiss = { showBottomSheet = false }
            )
        }
    }
}

@Composable
fun LanguageSection(
    description: String,
    language: Language,
    onClick: () -> Unit
) {
    Column {
        Text(
            text = description,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .clickable { onClick() }
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            when (language) {
                is Language.Empty -> {
                    Text(
                        text = "Select language",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                    )
                }

                is Language.Selected -> {
                    Text(
                        text = language.flag,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = language.languageName,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPickerBottomSheet(
    languageList: List<Language.Selected>,
    onCountrySelected: (Language.Selected) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Select country",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp),
            ) {
                itemsIndexed(
                    items = languageList,
                    key = { index, item -> item.id }
                ) { index, item ->
                    CountryItem(
                        language = item,
                        onClick = {
                            onCountrySelected(item)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CountryItem(
    language: Language.Selected,
    onClick: (Language.Selected) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(language) }
            .padding(
                top = 4.dp,
                bottom = 12.dp,
                start = 16.dp,
                end = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = language.flag,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = language.languageName,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
private fun CountryItemPreview() {
    CountryItem(
        language = Language.Selected.ENGLISH,
        onClick = {}
    )
}

@Preview
@Composable
private fun CountryPickerBottomSheetPreview() {
    CountryPickerBottomSheet(
        languageList = listOf(
            Language.Selected.ENGLISH,
            Language.Selected.POLISH
        ),
        onCountrySelected = {},
        onDismiss = { }
    )
}

@Preview(showBackground = true)
@Composable
fun LanguageSettingsScreenPreview() {

    val content = SettingsScreenContent(
        title = "Language Settings",
        subtitle = "Choose your learning languages",
        primaryDescription = "I speak",
        primary = Language.Selected.POLISH,
        secondaryDescription = "I want to learn",
        secondary = Language.Empty,
        buttonState = ButtonState(
            text = "Continue",
            active = true
        ),
        languages = Language.Selected.entries
    )
    SettingsPane(
        content = content,
        onLanguageSelected = { _, _ -> },
        onNextClicked = {}
    )
}
