package foop.fooper

import android.view.View

public class NormalProvider : MainCardProvider() {

    override fun onCreated() {
        super.onCreated()
        layout = R.layout.normal_layout

    }

    override fun render(view: View, card: Card) {
        super.render(view, card)
    }
}