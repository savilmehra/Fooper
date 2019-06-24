package foop.fooper

import android.view.View

public class NewProvider : MainCardProvider() {

    override fun onCreated() {
        super.onCreated()
        layout = R.layout.newprovider

    }

    override fun render(view: View, card: Card) {
        super.render(view, card)
    }
}