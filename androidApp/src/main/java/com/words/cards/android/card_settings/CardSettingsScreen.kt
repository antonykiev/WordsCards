package com.words.cards.android.card_settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.words.cards.android.design.CardButton
import com.words.cards.android.design.Logo
import com.words.cards.presentation.event.CardSettingsEvent
import com.words.cards.presentation.intent.CardSettingsIntent
import com.words.cards.presentation.state.CardSettingsScreenContent
import com.words.cards.presentation.state.State
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardSettingsScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    val viewModel: CardSettingsViewModel = koinViewModel<CardSettingsViewModel>()
    val state: State<CardSettingsScreenContent, CardSettingsEvent> by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onIntent(CardSettingsIntent.InitialLoad)
    }

    LaunchedEffect(state.event) {
        when (state.event) {
            CardSettingsEvent.Empty -> {}
            CardSettingsEvent.GoBack -> {
                onBack()
                viewModel.onEventHandled(CardSettingsEvent.GoBack)
            }
        }
    }

    CardSettingsPane(
        modifier = modifier,
        content = state.content,
        onBackClick = {
            viewModel.onIntent(CardSettingsIntent.OnBackClicked)
        },
        onShowTranscriptionToggled = {
            viewModel.onIntent(CardSettingsIntent.OnShowTranscriptionToggled(it))
        },
        onShowDescriptionToggled = {
            viewModel.onIntent(CardSettingsIntent.OnShowDescriptionToggled(it))
        },
        onShowTranslationToggled = {
            viewModel.onIntent(CardSettingsIntent.OnShowTranslationToggled(it))
        },
        onShowExampleToggled = {
            viewModel.onIntent(CardSettingsIntent.OnShowExampleToggled(it))
        }
    )
}

@Composable
fun CardSettingsPane(
    modifier: Modifier = Modifier,
    content: CardSettingsScreenContent,
    onBackClick: () -> Unit,
    onShowTranscriptionToggled: (Boolean) -> Unit,
    onShowDescriptionToggled: (Boolean) -> Unit,
    onShowTranslationToggled: (Boolean) -> Unit,
    onShowExampleToggled: (Boolean) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Logo()
            Spacer(Modifier.height(24.dp))
            Text(
                text = content.title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(32.dp))

            CardSettingsItem(
                label = content.showTranscriptionLabel,
                isEnabled = content.isShowTranscriptionEnabled,
                onToggled = onShowTranscriptionToggled
            )

            Spacer(Modifier.height(16.dp))

            CardSettingsItem(
                label = content.showDescriptionLabel,
                isEnabled = content.isShowDescriptionEnabled,
                onToggled = onShowDescriptionToggled
            )

            Spacer(Modifier.height(16.dp))

            CardSettingsItem(
                label = content.showTranslationLabel,
                isEnabled = content.isShowTranslationEnabled,
                onToggled = onShowTranslationToggled
            )

            Spacer(Modifier.height(16.dp))

            CardSettingsItem(
                label = content.showExampleLabel,
                isEnabled = content.isShowExampleEnabled,
                onToggled = onShowExampleToggled
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 24.dp),
        ) {
            CardButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Back",
                onClick = onBackClick
            )
        }
    }
}

@Composable
private fun CardSettingsItem(
    label: String,
    isEnabled: Boolean,
    onToggled: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Switch(
            checked = isEnabled,
            onCheckedChange = onToggled
        )
    }
}

@Preview
@Composable
private fun CardSettingsPanePreview() {
    CardSettingsPane(
        content = CardSettingsScreenContent(
            title = "Card Settings",
            showTranscriptionLabel = "Show Transcription",
            isShowTranscriptionEnabled = true,
            showDescriptionLabel = "Show Description",
            isShowDescriptionEnabled = true,
            showTranslationLabel = "Show Translation",
            isShowTranslationEnabled = true,
            showExampleLabel = "Show Example",
            isShowExampleEnabled = false,
        ),
        onBackClick = {},
        onShowTranscriptionToggled = {},
        onShowDescriptionToggled = {},
        onShowTranslationToggled = {},
        onShowExampleToggled = {}
    )
}
