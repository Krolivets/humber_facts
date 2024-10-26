package com.example.numberfacts.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.numberfacts.R
import com.example.numberfacts.database.NumberFact
import com.example.numberfacts.ui.theme.NumberFactsTheme

@Composable
fun DetailScreen(
    numberFact: NumberFact,
    onBack: () -> Unit = {},
) {
    Column(
        modifier =
            Modifier
                .padding(vertical = 48.dp, horizontal = 24.dp)
                .fillMaxWidth(),
    ) {
        Button(onClick = onBack) {
            Text("Back")
        }

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Number: ${numberFact.number}",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Fact: ${numberFact.fact}", style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    val sampleNumberFact =
        NumberFact(
            number = stringResource(R.string.sample_number),
            fact = stringResource(R.string.sample_text),
        )
    NumberFactsTheme {
        DetailScreen(sampleNumberFact)
    }
}
