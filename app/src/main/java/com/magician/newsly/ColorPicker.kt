package com.magician.newsly

object ColorPicker {
    val colors = arrayOf(
        "#FF007F", "#FF0000", "#FF7F00", "#FFFF00", "#7FFF00", "#00FF00",
        "#00FF7F", "#00FFFF", "#007FFF", "#0000FF", "#7F00FF", "#FF00FF"
    )

    var colorIndex = 1

    fun getColor(): String {
        return colors[colorIndex++ % colors.size]
    }
}