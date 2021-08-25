package com.example.savestate

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.savestate.Presenter.Contract
import com.example.savestate.Presenter.Presenter
import org.koin.android.ext.android.inject

class MainActivity() : AppCompatActivity(), Contract.View {

    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textView4: TextView
    private lateinit var textView5: TextView
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText
    private lateinit var editText5: EditText
    private lateinit var button: Button
    private var counter = 0
    val myKoin by inject<Presenter>()
    var getEditTextList = mutableListOf<Editable>()
    private var thisBundle = Bundle()
    var repository = com.example.savestate.repository.repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        if (thisBundle.containsKey("key")) {
            counter = thisBundle.get("key").toString().toInt()

        }

        myKoin.showViewActivity(this)
        button.setOnClickListener {
            myKoin.buttonClick(showTextView())
            thisBundle.putInt("key", counter)

        }
    }

    fun showTextView(): MutableList<Editable> {
        getEditTextList.add(editText1.text)
        getEditTextList.add(editText2.text)
        getEditTextList.add(editText3.text)
        getEditTextList.add(editText4.text)
        getEditTextList.add(editText5.text)
        return getEditTextList
    }

    fun init() {
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
        textView3 = findViewById(R.id.textView3)
        textView4 = findViewById(R.id.textView4)
        textView5 = findViewById(R.id.textView5)
        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)
        editText4 = findViewById(R.id.editText4)
        editText5 = findViewById(R.id.editText5)
        button = findViewById(R.id.button)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println("onSave " + thisBundle.get("key").toString())

        outState.run {
            putString("key", thisBundle.get("key").toString())
            putString("textView1", textView1.text.toString())
            putString("textView2", textView2.text.toString())
            putString("textView3", textView3.text.toString())
            putString("textView4", textView4.text.toString())
            putString("textView5", textView5.text.toString())
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        println("onRestor " + savedInstanceState.get("key").toString())
        thisBundle.putInt("key", savedInstanceState.get("key").toString().toInt())
        textView1.text = savedInstanceState.getString("textView1")
        textView2.text = savedInstanceState.getString("textView2")
        textView3.text = savedInstanceState.getString("textView3")
        textView4.text = savedInstanceState.getString("textView4")
        textView5.text = savedInstanceState.getString("textView5")

    }

    override fun onStop() {
        super.onStop()
        counter = thisBundle.get("key").toString().toInt()

        counter++

        thisBundle.putInt("key", counter)
        myKoin.saveState(thisBundle)
    }

    override fun showResult(getEditText: MutableList<Editable>) {
        textView1.text = getEditText[0]
        textView2.text = getEditText[1]
        textView3.text = getEditText[2]
        textView4.text = getEditText[3]
        textView5.text = getEditText[4]
    }

    override fun showToast(bundle: Bundle) {
        Toast.makeText(this, bundle.get("key").toString(), Toast.LENGTH_SHORT).show()
    }
}