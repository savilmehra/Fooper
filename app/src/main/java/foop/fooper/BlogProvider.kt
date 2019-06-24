package foop.fooper

import android.view.View

public class BlogProvider : MainCardProvider() {

    override fun onCreated() {
        super.onCreated()
        layout = R.layout.blog_provider

    }

    override fun render(view: View, card: Card) {
        super.render(view, card)
    }
}