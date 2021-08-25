package com.example.savestate.Presenter

import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView

interface Contract {

    interface Presenter{
        fun buttonClick(editable: MutableList<Editable>)
        fun saveState(bundle: Bundle)

    }

    interface View{
        fun showResult(getEditText: MutableList<Editable>)

        fun showToast(bundle: Bundle)
    }

}