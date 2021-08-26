package com.example.savestate.Presenter

import android.os.Bundle
import android.text.Editable

class Presenter: Contract.Presenter {

    var showview: Contract.View? = null
    fun showViewActivity(view: Contract.View){
        showview = view
    }

    override fun buttonClick(editable: MutableList<Editable>) {
        showview?.showResult(editable)
    }

    override fun saveState(bundle: Bundle) {
        showview?.showToast(bundle)
    }

}


