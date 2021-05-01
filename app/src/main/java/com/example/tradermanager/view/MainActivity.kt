package com.example.tradermanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tradermanager.R
import com.example.tradermanager.databinding.ActivityMainBinding
import com.example.tradermanager.viewmodel.OperationViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var mOperationViewModel: OperationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mOperationViewModel = ViewModelProvider(this).get(OperationViewModel::class.java)
        setClicklListener()
        observer()

    }

    private fun observer(){}

    private fun setClicklListener(){
        binding.btnAddOperation.setOnClickListener {
            val inputPoint = binding.inputPoint.editText!!.text.toString()
            val outputPoint = binding.outputPoint.editText!!.text.toString()
            val contract = binding.quantityContract.editText!!.text.toString()
        }
    }
}