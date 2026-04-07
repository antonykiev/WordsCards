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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
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
import com.words.cards.presentation.state.Vocabulary
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
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
        }
    }

    SettingsPane(
        modifier = modifier,
        content = state.content,
        onLanguageSelected = { vocabularyId, isPrimary, language ->


        }
    )
}

@Composable
fun SettingsPane(
    modifier: Modifier = Modifier,
    content: SettingsScreenContent,
    onLanguageSelected: (Long, Boolean, Language.Selected) -> Unit
) {
    var selectedVocabularyId by remember { mutableStateOf<Long?>(null) }
    var isPrimarySelected by remember { mutableStateOf(true) }
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(
                content.vocabularies,
                key = { index, item -> item.id }
            ) { index, vocab ->
                VocabularyRow(
                    vocabulary = vocab,
                    onPrimaryClick = {
                        selectedVocabularyId = vocab.id
                        isPrimarySelected = true
                        showBottomSheet = true
                    },
                    onSecondaryClick = {
                        selectedVocabularyId = vocab.id
                        isPrimarySelected = false
                        showBottomSheet = true
                    }
                )
            }
        }

        if (showBottomSheet && selectedVocabularyId != null) {
            CountryPickerBottomSheet(
                languageList = content.languages,
                onCountrySelected = { selected ->
                    onLanguageSelected(
                        selectedVocabularyId!!,
                        isPrimarySelected,
                        selected
                    )
                    showBottomSheet = false
                },
                onDismiss = {
                    showBottomSheet = false
                }
            )
        }
    }
}

@Composable
fun VocabularyRow(
    vocabulary: Vocabulary,
    onPrimaryClick: () -> Unit,
    onSecondaryClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        LanguageBox(
            language = vocabulary.primary,
            onClick = onPrimaryClick
        )

        Text(
            text = "→",
            style = MaterialTheme.typography.titleMedium
        )

        LanguageBox(
            language = vocabulary.secondary,
            onClick = onSecondaryClick
        )
    }
}

@Composable
fun LanguageBox(
    language: Language,
    onClick: () -> Unit
) {
    val text = when (language) {
        is Language.Empty -> "Select"
        is Language.Selected -> "${language.flag} ${language.name}"
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(text = text)
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
            text = language.name,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
private fun CountryItemPreview() {
    CountryItem(
        language = Language.Selected(
            id = 1,
            flag = "🇺🇸",
            name = "United States"
        ),
        onClick = {}
    )
}

@Preview
@Composable
private fun CountryPickerBottomSheetPreview() {
    CountryPickerBottomSheet(
        languageList = listOf(
            Language.Selected(
                id = 1,
                flag = "🇺🇸",
                name = "United States"
            ),
            Language.Selected(
                id = 2,
                flag = "🇬🇧",
                name = "United Kingdom"
            )
        ),
        onCountrySelected = {},
        onDismiss = { }
    )
}

@Preview
@Composable
fun SettingsPanePreview() {
    SettingsPane(
        content = SettingsScreenContent(
            vocabularies = listOf(
                Vocabulary(
                    id = 1,
                    primary = Language.Selected(
                        id = 1,
                        flag = "🇺🇸",
                        name = "United States"
                    ),
                    secondary = Language.Selected(
                        id = 2,
                        flag = "🇬🇧",
                        name = "United Kingdom"
                    )
                )
            ),
            languages = listOf(
                Language.Selected(
                    id = 1,
                    flag = "🇺🇸",
                    name = "United States"
                ),
                Language.Selected(
                    id = 2,
                    flag = "🇬🇧",
                    name = "United Kingdom"
                )
            ),
            buttonState = ButtonState(
                text = "Next",
                active = true
            ),

            ),
        onLanguageSelected = { _, _, _ -> }
    )
}

