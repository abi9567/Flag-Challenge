package com.abi.flagchallenge.data

import androidx.annotation.DrawableRes
import com.abi.flagchallenge.R
import com.google.gson.annotations.SerializedName

data class Questions(

    @SerializedName("answer_id")
    val answerId : Int?,

    @DrawableRes val questionFlag : Int,

    @SerializedName("countries")
    val countries : List<Countries>
) {
    companion object {
        val QUESTIONS = listOf(
            Questions(
                answerId = 160,
                questionFlag = R.drawable.ic_newzland,
                countries = listOf(
                    Countries("Bosnia and Herzegovina",29),
                    Countries("Mauritania", 142),
                    Countries("Chile", 45),
                    Countries("New Zealand", 160)
                )
            ),
            Questions(
                answerId = 13,
                questionFlag = R.drawable.ic_aruba,
                countries = listOf(
                    Countries("Aruba", 13),
                    Countries("Serbia", 184),
                    Countries("Montenegro", 150),
                    Countries("Moldova", 147)
                )
            ),
            Questions(
                answerId = 66,
                questionFlag = R.drawable.ic_ecuador,
                countries = listOf(
                    Countries("Kenya", 117),
                    Countries("Montenegro", 150),
                    Countries("Ecuador", 66),
                    Countries("Bhutan", 26)
                )
            ),
            Questions(
                answerId = 174,
                questionFlag = R.drawable.ic_parahuay,
                countries = listOf(
                    Countries("Niue", 164),
                    Countries("Paraguay", 174),
                    Countries("Tuvalu", 232),
                    Countries("Indonesia", 105)
                )
            ),
            Questions(
                answerId = 122,
                questionFlag = R.drawable.ic_kyrgiztan,
                countries = listOf(
                    Countries("Kyrgyzstan", 122),
                    Countries("Zimbabwe", 250),
                    Countries("Saint Lucia", 190),
                    Countries("Ireland", 108)
                )
            ),
            Questions(
                answerId = 192,
                questionFlag = R.drawable.saint_pierre_and_miquelon__pm_,
                countries = listOf(
                    Countries("Saint Pierre and Miquelon", 192),
                    Countries("Namibia", 155),
                    Countries("Greece", 87),
                    Countries("Anguilla", 8)
                )
            ),
            Questions(
                answerId = 113,
                questionFlag = R.drawable.japan__jp_,
                countries = listOf(
                    Countries("Belarus", 21),
                    Countries("Falkland Islands", 73),
                    Countries("Japan", 113),
                    Countries("Iraq", 107)
                )
            ),
            Questions(
                answerId = 230,
                questionFlag = R.drawable.turkmenistan__tm_,
                countries = listOf(
                    Countries("Barbados", 20),
                    Countries("Italy", 111),
                    Countries("Turkmenistan", 230),
                    Countries("Cocos Island", 48)
                )
            ),
            Questions(
                answerId = 81,
                questionFlag = R.drawable.ic_gabon__ga_,
                countries = listOf(
                    Countries("Maldives", 137),
                    Countries("Aruba", 13),
                    Countries("Monaco", 148),
                    Countries("Gabon", 81)
                )
            ),
            Questions(
                answerId = 141,
                questionFlag = R.drawable.martinique__mq_,
                countries = listOf(
                    Countries("Martinique", 141),
                    Countries("Montenegro", 150),
                    Countries("Barbados", 20),
                    Countries("Monaco", 148)
                )
            ),
            Questions(
                answerId = 23,
                questionFlag = R.drawable.ic_belize__bz_,
                countries = listOf(
                    Countries("Germany", 84),
                    Countries("Dominica", 63),
                    Countries("Belize", 23),
                    Countries("Tuvalu", 232)
                )
            ),
            Questions(
                answerId = 60,
                questionFlag = R.drawable.ic_czech_republic__cz_,
                countries = listOf(
                    Countries("Falkland Islands", 73),
                    Countries("Czech Republic", 60),
                    Countries("Mauritania", 142),
                    Countries("British Indian Ocean Territory", 33)
                )
            ),
            Questions(
                answerId = 235,
                questionFlag = R.drawable.united_arab_emirates__ae_,
                countries = listOf(
                    Countries("United Arab Emirates", 235),
                    Countries("United Arab Emirates", 235),
                    Countries("Macedonia", 133),
                    Countries("Guernsey", 93)
                )
            ),
            Questions(
                answerId = 114,
                questionFlag = R.drawable.jersey__je_,
                countries = listOf(
                    Countries("Turks and Caicos Islands", 231),
                    Countries("Myanmar", 154),
                    Countries("Jersey", 114),
                    Countries("Ethiopia", 72)
                )
            ),
            Questions(
                answerId = 126,
                questionFlag = R.drawable.lesotho__ls_,
                countries = listOf(
                    Countries("Malawi", 135),
                    Countries("Trinidad and Tobago", 227),
                    Countries("Nepal", 157),
                    Countries("Lesotho", 126)
                )
            )
        )
    }
}
