package com.finora.app.presentation.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val BottomSheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceBottomSheet(
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = rememberModalBottomSheetState(),
        shape = BottomSheetShape,
        content = content,
    )
}
