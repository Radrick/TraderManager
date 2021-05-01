package com.example.tradermanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tradermanager.domain.OperationModel
import com.example.tradermanager.service.constants.OperationConstants
import com.example.tradermanager.service.db.OperationRepository

class OperationViewModel(application: Application): AndroidViewModel(application) {

    private var mOperationRepository: OperationRepository = OperationRepository.getInstance(application)


    private var mSaveOperation = MutableLiveData<Boolean>()
    val mOperation: LiveData<Boolean> = mSaveOperation

    fun save(inputPoint: Int, outputPoint: Int, quantityContract: Int){
        val resultPoint = calculatePoints(inputPoint,outputPoint)
        val resultFinance: Double = calculateFinance(quantityContract, resultPoint)
        val mOperationModel = OperationModel(inputPoint = inputPoint,outputPoint = outputPoint,quantityContract = quantityContract,
                resultPoint = resultPoint, resultFinance = resultFinance)

        mSaveOperation.value = mOperationRepository.save(mOperationModel)
    }

    fun calculatePoints(inputPoint: Int, outputPoint: Int) = inputPoint.minus(outputPoint)

    fun calculateFinance(quantityContract: Int, resultPoint: Int): Double = quantityContract.plus(resultPoint).plus(OperationConstants.PRICE)

}