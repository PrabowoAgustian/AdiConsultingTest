package com.dirumahajanews.helper

import android.annotation.SuppressLint
import android.text.Spannable
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.RelativeSizeSpan
import android.util.Patterns
import org.apache.commons.lang3.StringUtils
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class StringHelper {

    companion object {
        fun getStringBuilderToString(vararg items: String): String {
            val stringBuilder = StringBuilder()
            for (s in items) {
                stringBuilder.append(s)
            }
            return stringBuilder.toString()
        }

        fun getStringBuilderToStringFromList(list: List<String>): String {
            return getStringBuilderToStringFromList(list, ", ")
        }

        fun getStringBuilderToStringFromList(list: CharArray): String {
            return getStringBuilderToStringFromList(list, ", ")
        }

        fun getStringBuilderToStringFromList(list: List<String>, divider: String): String {
            val stringBuilder = StringBuilder()
            val i = list.size - 1
            for (x in list.indices) {
                stringBuilder.append(list[x])
                if (x != i) {
                    stringBuilder.append(divider)
                }
            }
            return stringBuilder.toString()
        }

        fun getStringBuilderToStringFromList(
            list: List<String>,
            divider: String,
            limit: Int?
        ): String {
            val stringBuilder = StringBuilder()
            val i = list.size - 1
            for (x in list.indices) {
                stringBuilder.append(list[x])
                if (x == limit!! - 1)
                    break
                if (x != i) {
                    stringBuilder.append(divider)
                }
            }
            return stringBuilder.toString()
        }

        fun getStringBuilderToStringFromList(list: CharArray, divider: String): String {
            val stringBuilder = StringBuilder()
            val i = list.size - 1
            for (x in list.indices) {
                stringBuilder.append(list[x])
                if (x != i) {
                    stringBuilder.append(divider)
                }
            }
            return stringBuilder.toString()
        }

        fun getPriceInRp(price: Double?, currency: String): String {
            return if (price != null)
                getStringBuilderToString(
                    if (StringUtils.isNotBlank(currency)) currency else "IDR",
                    " ",
                    getSimplePriceFormatter(price)
                )
            else
                ""
        }

        fun getSimplePriceFormatter(price: Double?): String {
            return String.format("%,.0f", price).replace(",", ".")
        }

        fun getSimplePriceFormatterWithoutIndent(price: Double?): String {
            return String.format("%,.2f", price).replace(",", ".")
                .replace("\\.(\\d+)$".toRegex(), ",$1")
                .replace(",([1-9])(0)$".toRegex(), ",$1").replace(",(00)$".toRegex(), "")
        }

        fun getSimplePriceFormatter(price: BigDecimal): String {
            return String.format("%,.0f", price.toDouble()).replace(",", ".")
        }

        fun getSimplePriceFormatter(price: Long): String {
            return String.format("%,.0f", price.toDouble()).replace(",", ".")
        }

        fun getSimplePriceFormatterInRp(price: Double?): String {
            return getStringBuilderToString("IDR ", getSimplePriceFormatter(price))
        }

        fun getPriceInRp(price: Double?): String {
            return if (price != null)
                getStringBuilderToString("IDR ", getSimplePriceFormatter(price))
            else
                ""
        }

        fun getPriceInRp(price: BigDecimal?): String {
            return if (price != null)
                getStringBuilderToString("IDR ", getSimplePriceFormatter(price.toDouble()))
            else
                ""
        }

        fun getPriceInRp(price: String): String {
            return if (StringUtils.isNotBlank(price))
                if (price.contains("IDR"))
                    price
                else
                    getStringBuilderToString(
                        "IDR ",
                        getSimplePriceFormatter(
                            java.lang.Double.valueOf(
                                removeDotFromFormatedValue(
                                    price
                                )
                            )
                        )
                    )
            else
                ""
        }

        fun getPriceInRp(price: Long?): String {
            return getPriceInRp(java.lang.Double.valueOf((price ?: 0).toDouble()))
        }

        fun removeDotFromFormatedValue(price: String): String {
            return price.replace(".", "")
        }

        fun removeRpFromValue(price: String): String {
            return removeDotFromFormatedValue(price).replace("IDR ", "")
        }


        @SuppressLint("DefaultLocale")
        fun capitalizeFirstLetter(original: String?): String? {
            return if (original == null || original.isEmpty()) {
                original
            } else {
                val newWord = original.toLowerCase()
                newWord.substring(0, 1).toUpperCase() + newWord.substring(1)
            }
        }

        fun splitString(divider: String, value: String): Array<String> {
            return value.split(divider.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }

        fun isUrl(url: String): Boolean {
            return StringUtils.isNotBlank(url) && (url.contains("http://") || url.contains("https://"))
        }

        fun isEmail(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isPhone(phone: String): Boolean {
            return Patterns.PHONE.matcher(phone).matches()
        }

        fun getFormatterToLongDigitValue(price: String): String {
            val formatter = DecimalFormat("#,###,###,###")
            return formatter.format(BigDecimal(price)).replace(",", "")
                .replace(".", "")
        }

        fun getFormatterToLongDigitValue(price: BigDecimal?): String? {
            val formatter = DecimalFormat("#,###,###,###")
            return if (price != null)
                formatter.format(price).replace(",", "")
                    .replace(".", "")
            else
                null
        }

        fun removeTrailingZeros(value: Double?): String {
            if (value != null) {
                val df = DecimalFormat("###.#")
                return df.format(value)
            } else {
                return ""
            }
        }

        fun removeTrailingZerosWithTwoDigitsDecimalAndRoundingFloor(value: Double?): String {
            if (value != null) {
                val df = DecimalFormat("###.##")
                val newValue = BigDecimal.valueOf(value)
                    .setScale(if (value > 100) 0 else 1, RoundingMode.FLOOR).toDouble()
                return convertCommaToDot(df.format(newValue))
            } else {
                return ""
            }
        }

        fun removeTrailingZerosWithTwoDigitsDecimalAndRoundingFloorAndCommaDecimalSeparator(value: Double?): String {
            if (value != null) {
                val otherSymbols = DecimalFormatSymbols()
                otherSymbols.decimalSeparator = ','
                otherSymbols.groupingSeparator = '.'
                val df = DecimalFormat("###.##", otherSymbols)
                val newValue =
                    BigDecimal.valueOf(value).setScale(2, RoundingMode.FLOOR).toDouble()
                return df.format(newValue)
            } else {
                return ""
            }
        }

        fun getDecimalFormatter(price: String): String {
            val formatter = DecimalFormat("#,###,###,###")
            return formatter.format(BigDecimal(price))
                .replace(",", ".")
        }

        fun getDecimalWithCommasFormatter(price: String): String {
            val otherSymbols = DecimalFormatSymbols()
            otherSymbols.decimalSeparator = ','
            otherSymbols.groupingSeparator = '.'
            val formatter = DecimalFormat("#.00")
            return formatter.format(BigDecimal(price))
        }

        fun convertCommaToDot(value: String): String {
            return value.replace(",", ".")
        }

        fun getContactFormated(kontak: String): String {
            if (StringUtils.isNotBlank(kontak)) {
                val newKontak = kontak.replace("[^\\d+]".toRegex(), "")
                return if (newKontak.contains("+62"))
                    newKontak
                else
                    if (newKontak.substring(0, 1) == "0")
                        newKontak.replaceFirst(newKontak.substring(0, 1).toRegex(), "+62")
                    else
                        getStringBuilderToString("+62", newKontak)
            }
            return ""
        }

        fun getContactFormatedNew(kontak: String): String {
            return getContactFormated(kontak).replace("+62", "")
        }

        fun getContactSent(kontak: String): String {
            return kontak.replace("+62", "0")
        }

        fun getSafeSubstring(s: String, maxLength: Int): String? {
            if (!TextUtils.isEmpty(s)) {
                if (s.length >= maxLength) {
                    return s.substring(0, maxLength) + " ..."
                }
            }
            return s
        }

        fun getSafeSubstringLocation(s: String, maxLength: Int): String? {
            if (!TextUtils.isEmpty(s)) {
                if (s.length >= maxLength) {
                    return s.substring(0, maxLength) + "..."
                }
            }
            return s
        }

        fun convertDecimalTwoDigit(digit: Long): String {
            return if (digit < 10)
                "0$digit"
            else
                (digit).toString()
        }

        fun convertIntToValue(digit: Int): String {
            return if (digit < 10)
                "0$digit"
            else
                (digit).toString()
        }

        fun getStringBuilderToStringDouble(vararg items: Double): String {
            val stringBuilder = StringBuilder()
            for (s in items) {
                stringBuilder.append(s)
            }
            return stringBuilder.toString()
        }

        fun increaseFontSizeForPath(spannable: Spannable, path: String, increaseTime: Float) {
            val startIndexOfPath = spannable.toString().indexOf(path)
            spannable.setSpan(
                RelativeSizeSpan(increaseTime), startIndexOfPath,
                startIndexOfPath + path.length, 0
            )
        }

        fun setFontSizeForPath(spannable: Spannable, path: String, fontSizeInPixel: Int) {
            val startIndexOfPath = spannable.toString().indexOf(path)
            spannable.setSpan(
                AbsoluteSizeSpan(fontSizeInPixel), startIndexOfPath,
                startIndexOfPath + path.length, 0
            )
        }
    }
}