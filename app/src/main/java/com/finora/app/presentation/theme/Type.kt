package com.finora.app.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.finora.app.R

/**
 * Inter, fetched via the Downloadable Fonts API (Google Play Services), per the
 * "Fintech Precision" design system (DESIGN.md: `fontFamily: Inter` everywhere). Falls back
 * to the platform default automatically if Play Services / network is unavailable.
 */
private val googleFontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs,
)

private val interGoogleFont = GoogleFont("Inter")

private fun interFamily(weight: FontWeight) = FontFamily(
    Font(googleFont = interGoogleFont, fontProvider = googleFontProvider, weight = weight),
)

// One FontFamily per weight used by the type scale (DESIGN.md only uses 400/600/700).
private val InterRegular = interFamily(FontWeight.Normal)
private val InterSemiBold = interFamily(FontWeight.SemiBold)
private val InterBold = interFamily(FontWeight.Bold)

/** Matches DESIGN.md's `balance-lg` token — used directly by BalanceCard, not part of the M3 scale. */
val BalanceLargeTextStyle = TextStyle(
    fontFamily = InterSemiBold,
    fontWeight = FontWeight.SemiBold,
    fontSize = 36.sp,
    lineHeight = 44.sp,
)

/** Matches DESIGN.md's `label-caps` token (all-caps metadata labels with wide tracking). */
val LabelCapsTextStyle = TextStyle(
    fontFamily = InterBold,
    fontWeight = FontWeight.Bold,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    letterSpacing = 0.66.sp, // 0.06em @ 11sp
)

val FinoraTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = InterBold,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 48.sp,
    ),
    displayMedium = BalanceLargeTextStyle,
    displaySmall = TextStyle(
        fontFamily = InterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = InterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = InterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = InterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = InterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = InterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = InterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = InterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = InterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = InterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = InterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelMedium = LabelCapsTextStyle,
    labelSmall = TextStyle(
        fontFamily = InterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 14.sp,
    ),
)
