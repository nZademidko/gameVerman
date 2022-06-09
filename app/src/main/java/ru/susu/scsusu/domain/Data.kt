package ru.susu.scsusu.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.susu.scsusu.R
import ru.susu.scsusu.presentation.plots.GameType

@Parcelize
data class ButtonRes(
    val gameType: Int = GameType.NOTHING,
    val id: Int = -1,
    val navigationId: Int = -1,
    val level: Int = -1,
    val maxLevel: Int = -1
) : Parcelable

val dataMap =
    mapOf(
        R.string.text_101 to listOf(
            ButtonRes(
                GameType.NOTHING,
                R.string.button_0,
                R.string.text_102
            )
        ),
        R.string.text_102 to listOf(
            ButtonRes(
                GameType.NOTHING,
                R.string.button_0,
                R.string.text_103
            )
        ),
        R.string.text_103 to listOf(
            ButtonRes(GameType.NOTHING, R.string.button_101, R.string.text_104),
            ButtonRes(GameType.NOTHING, R.string.button_102, R.string.text_107)
        ),
        R.string.text_104 to listOf(
            ButtonRes(GameType.NOTHING, R.string.button_103, R.string.text_105),
            ButtonRes(GameType.NOTHING, R.string.button_104, R.string.text_106)
        ),
        R.string.text_105 to listOf(
            ButtonRes(
                GameType.NOTHING,
                R.string.button_0,
                R.string.text_109
            )
        ),
        R.string.text_106 to listOf(
            ButtonRes(
                GameType.NOTHING,
                R.string.button_0,
                R.string.text_109
            )
        ),
        R.string.text_107 to listOf(
            ButtonRes(GameType.NOTHING, R.string.button_105, R.string.text_108),
            ButtonRes(GameType.NOTHING, R.string.button_106, R.string.text_104)
        ),
        R.string.text_108 to listOf(
            ButtonRes(
                GameType.NOTHING,
                R.string.button_0,
                R.string.text_109
            )
        ),
        R.string.text_109 to listOf(
            ButtonRes(
                GameType.NOTHING,
                R.string.button_0,
                R.string.text_110
            )
        ),
        R.string.text_110 to listOf(
            //ButtonRes(false, R.string.button_107, R.string.text_130),
            ButtonRes(GameType.MATH_PROBLEM, R.string.button_108, R.string.text_111),
            //ButtonRes(false, R.string.button_109, R.string.text_131)
        ),
        R.string.text_111 to listOf(
            ButtonRes(GameType.NOTHING, R.string.button_110, R.string.text_112),
            //ButtonRes(false, R.string.button_111, R.string.text_132)
        ),
        R.string.text_112 to listOf(
            ButtonRes(
                GameType.NOTHING,
                R.string.button_112,
                R.string.text_113
            )
        ),
        R.string.text_113 to listOf(
            ButtonRes(GameType.MATH_PROBLEM, R.string.button_113, R.string.text_114),
            ButtonRes(GameType.REPEAT_SEQUENCE, R.string.button_114, R.string.text_121)
        ),
        R.string.text_114 to listOf(
            ButtonRes(GameType.REPEAT_SEQUENCE, R.string.button_115, R.string.text_115),
            ButtonRes(GameType.REPEAT_SEQUENCE, R.string.button_116, R.string.text_120, 5, 7)
        ),
        R.string.text_115 to listOf(
            ButtonRes(GameType.NOTHING, R.string.button_117, R.string.text_116),
            ButtonRes(GameType.NOTHING, R.string.button_118, R.string.text_118)
        ),
        R.string.text_116 to listOf(
            ButtonRes(
                GameType.REPEAT_SEQUENCE,
                R.string.button_119,
                R.string.text_117,
                4,
                6
            )
        ),
        R.string.text_117 to listOf(ButtonRes(GameType.NOTHING, R.string.button_1, 1000)),
        R.string.text_118 to listOf(
            ButtonRes(
                GameType.REPEAT_SEQUENCE,
                R.string.button_120,
                R.string.text_119
            )
        ),
        R.string.text_119 to listOf(ButtonRes(GameType.NOTHING, R.string.button_1, 1000)),
        R.string.text_120 to listOf(ButtonRes(GameType.NOTHING, R.string.button_1, 1000)),
        R.string.text_121 to listOf(
            ButtonRes(GameType.MATH_PROBLEM, R.string.button_121, R.string.text_114),
            ButtonRes(GameType.NOTHING, R.string.button_122, R.string.text_122)
        ),
        R.string.text_122 to listOf(
            ButtonRes(
                GameType.MATH_PROBLEM,
                R.string.button_01,
                R.string.text_123,
                3,
                4
            )
        ),
        R.string.text_123 to listOf(
            ButtonRes(GameType.REPEAT_SEQUENCE, R.string.button_123, R.string.text_124),
            ButtonRes(GameType.NOTHING, R.string.button_124, R.string.text_127),
        ),
        R.string.text_124 to listOf(
            ButtonRes(GameType.MATH_PROBLEM, R.string.button_125, R.string.text_125),
            ButtonRes(GameType.NOTHING, R.string.button_126, R.string.text_126),
        ),
        R.string.text_125 to listOf(
            ButtonRes(
                GameType.NOTHING,
                R.string.button_1,
                1000
            )
        ),
        R.string.text_126 to listOf(
            ButtonRes(
                GameType.NOTHING,
                R.string.button_1,
                1000
            )
        ),
        R.string.text_127 to listOf(
            ButtonRes(GameType.NOTHING, R.string.button_125, R.string.text_128),
            ButtonRes(GameType.NOTHING, R.string.button_126, R.string.text_129),
        ),
        R.string.text_128 to listOf(
            ButtonRes(
                GameType.NOTHING,
                R.string.button_1,
                1000            )
        ),
        R.string.text_129 to listOf(
            ButtonRes(
                GameType.NOTHING,
                R.string.button_1,
                1000
            )
        ),
    )