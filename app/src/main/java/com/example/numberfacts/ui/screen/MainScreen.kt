package com.example.numberfacts.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.numberfacts.R
import com.example.numberfacts.database.NumberFact
import com.example.numberfacts.ui.theme.NumberFactsTheme
import com.example.numberfacts.viewmodel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navigateToDetail: (NumberFact) -> Unit = {},
) {
    val facts by viewModel.facts.observeAsState(emptyList())

    MainScreenContent(
        facts = facts,
        onGetFactClick = { viewModel.getFact(it, isRandom = false) },
        onGetRandomFactClick = { viewModel.getFact("", isRandom = true) },
        navigateToDetail = navigateToDetail,
    )
}

@Composable
fun MainScreenContent(
    facts: List<NumberFact>,
    onGetFactClick: (String) -> Unit,
    onGetRandomFactClick: () -> Unit,
    navigateToDetail: (NumberFact) -> Unit = {},
) {
    Column(modifier = Modifier.padding(vertical = 29.dp, horizontal = 24.dp)) {
        var numberInput by remember { mutableStateOf("") }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = numberInput,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        numberInput = input
                    }
                },
                label = { Text(stringResource(R.string.enter_number)) },
                keyboardOptions =
                    KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                    ),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onGetFactClick(numberInput) },
                enabled = numberInput.isNotBlank(),
            ) {
                Text(
                    text = stringResource(R.string.get_fact),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onGetRandomFactClick() },
            ) {
                Text(
                    text = stringResource(R.string.get_fact_about_random_number),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(facts) { fact ->
                Text(
                    text =
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Number ${fact.number}: ")
                            }
                            append(fact.fact.take(50) + "...")
                        },
                    modifier = Modifier.clickable { navigateToDetail(fact) },
                    maxLines = 1,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    NumberFactsTheme {
        MainScreenContent(
            facts =
                listOf(
                    NumberFact(
                        number = stringResource(R.string.sample_number),
                        fact = stringResource(R.string.sample_text),
                    ),
                    NumberFact(
                        number = stringResource(R.string.sample_number),
                        fact = stringResource(R.string.sample_text),
                    ),
                    NumberFact(
                        number = stringResource(R.string.sample_number),
                        fact = stringResource(R.string.sample_text),
                    ),
                ),
            onGetFactClick = {},
            onGetRandomFactClick = {},
        )
    }
}
