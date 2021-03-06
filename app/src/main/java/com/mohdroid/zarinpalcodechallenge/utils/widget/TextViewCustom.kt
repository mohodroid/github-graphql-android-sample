package com.mohdroid.zarinpalcodechallenge.utils.widget

import android.content.Context
import android.graphics.Typeface
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class TextViewCustom : AppCompatTextView {
    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        typeface = Typeface.createFromAsset(context.assets, "fonts/IRANSans.ttf")
        movementMethod = LinkMovementMethod.getInstance()
        this.typeface = typeface
    }
}