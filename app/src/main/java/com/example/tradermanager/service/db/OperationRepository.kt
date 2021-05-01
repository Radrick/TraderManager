package com.example.tradermanager.service.db

import android.content.ContentValues
import android.content.Context
import com.example.tradermanager.domain.OperationModel
import com.example.tradermanager.service.constants.OperationDatabaseConstants.*

class OperationRepository private constructor(context: Context) {

    private val mDataBaseHelper: DataBaseHelper = DataBaseHelper(context)

    //singleton partiner
    companion object{
        private lateinit var mRepository: OperationRepository
        fun getInstance(context: Context): OperationRepository{
            if (!Companion::mRepository.isInitialized){
                mRepository = OperationRepository(context)
            }
            return mRepository
        }
    }

    fun save(operation: OperationModel): Boolean{
        try {
            val db = mDataBaseHelper.writableDatabase
            val contentValue = ContentValues().apply {
                put(OPERATION.COLUMNS.INPUT_POINT, operation.inputPoint)
                put(OPERATION.COLUMNS.OUTPUT_POINT, operation.outputPoint)
                put(OPERATION.COLUMNS.QUANTITY_CONTRACT, operation.quantityContract)
                put(OPERATION.COLUMNS.RESULT_POINT, operation.resultPoint)
                put(OPERATION.COLUMNS.RESULT_FINANCE, operation.resultFinance)
            }
            db.insert(OPERATION.TABLE_NAME, null, contentValue)
            db.close()
            return true
        } catch (e: Exception){
            return false
        }
    }

    fun update(operation: OperationModel): Boolean{
        try {
            val db = mDataBaseHelper.writableDatabase
            val contentValues = ContentValues().apply {
                put(OPERATION.COLUMNS.INPUT_POINT, operation.inputPoint)
                put(OPERATION.COLUMNS.OUTPUT_POINT, operation.outputPoint)
                put(OPERATION.COLUMNS.QUANTITY_CONTRACT, operation.quantityContract)
                put(OPERATION.COLUMNS.RESULT_POINT, operation.resultPoint)
                put(OPERATION.COLUMNS.RESULT_FINANCE, operation.resultFinance)
            }
            val selection = OPERATION.COLUMNS.ID + " = ? "
            val args = arrayOf(operation.id.toString())
            db.update(OPERATION.TABLE_NAME, contentValues, selection, args)
            db.close()
            return true
        } catch (e: Exception){
            return false
        }
    }

    fun delete(id: Int): Boolean{
        try {
            val db = mDataBaseHelper.writableDatabase
            val selection = OPERATION.COLUMNS.ID + " = ? "
            val args = arrayOf(id.toString())
            db.delete(OPERATION.TABLE_NAME, selection,args)
            db.close()
            return true
        } catch (e: Exception){
            return false
        }
    }

    fun listAll(id: Int): List<OperationModel>?{
        var operationList: MutableList<OperationModel>? = ArrayList()
        return operationList
    }

}