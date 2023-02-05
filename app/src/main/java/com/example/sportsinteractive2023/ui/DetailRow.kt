package com.example.sportsinteractive2023.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.sportsinteractive2023.R
import com.example.sportsinteractive2023.databinding.DetailRowBinding

class DetailRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr)  {

    private val binding = DetailRowBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DetailRow)
        binding.tvLabel.text = attributes.getText(R.styleable.DetailRow_label)
        binding.tvValue.text = attributes.getText(R.styleable.DetailRow_value)
        attributes.recycle()
    }

    fun setLabel(label:String){
        binding.tvLabel.text = label
    }

    fun setValue(value:String?){
        if(value==null){
            binding.tvValue.text = "-"
        }else{
            binding.tvValue.text = value
        }

    }
}