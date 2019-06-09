package com.tahn.article_nyt.feature.Main

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import com.tahn.article_nyt.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.filter_fragment.view.*


class FilterDialog: DialogFragment() {
    var begin_date : String? = null
    var sort_order : String? = null
    var values : String? = null
    var onInputListener : OnInputListener? = null

    interface OnInputListener{
        fun sendInput(date : String?, sort : String?, values : String?)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View? = inflater?.inflate(R.layout.filter_fragment, container, false)
        dialog.window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window.setDimAmount(0.8f)
        return view!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var rg = view.findViewById<RadioGroup>(R.id.fm_radioGroup)
        rg.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_arts -> values = "day_of_week"
                R.id.rb_fs -> values = "document_type"
                R.id.rb_sport -> values = "ingredients"
            }
        }

        var btn = view.findViewById<Button>(R.id.fm_buttonSave)
        btn.setOnClickListener {
            if(!view.fm_editTextBeginDate.text.toString().matches(datePattern().toRegex())){
                Toast.makeText(activity, "Not match with yyyy/mm/dd", Toast.LENGTH_LONG).show()
            }else{
                begin_date = if(view.fm_editTextBeginDate.text.toString().isEmpty()){
                    ""
                }else{
                    getBeginDate(view.fm_editTextBeginDate.text.toString())
                }
                sort_order = view.fm_spinnerSort.selectedItem.toString().toLowerCase()

                if(values == null){
                    values = ""
                }
                onInputListener?.sendInput(begin_date, sort_order, values)
                dialog.dismiss()
                Toast.makeText(activity, "Have Filter", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        onInputListener = activity as OnInputListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun getBeginDate(beginDate : String) : String{
        val processString = beginDate.split("/")
        val beginDate = processString[0] + processString[1] + processString[2]
        return beginDate
    }

    fun datePattern(): String = """\d{4}\/\d{2}\/\d{2}"""
}