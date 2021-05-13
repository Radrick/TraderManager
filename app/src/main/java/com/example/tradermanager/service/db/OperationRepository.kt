package com.example.tradermanager.service.db

import android.content.ContentValues
import android.content.Context
import com.example.tradermanager.domain.OperationModel
import com.example.tradermanager.service.constants.OperationDatabaseConstants.*

class OperationRepository private constructor(context: Context) {

    private val mDataBaseHelper: DataBaseHelper = DataBaseHelper(context)

    //singleton partiner
    companion object {
        private lateinit var mRepository: OperationRepository
        fun getInstance(context: Context): OperationRepository {
            if (!Companion::mRepository.isInitialized) {
                mRepository = OperationRepository(context)
            }
            return mRepository
        }
    }

    fun save(operation: OperationModel): Boolean {
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
        } catch (e: Exception) {
            return false
        }
    }

    fun update(operation: OperationModel): Boolean {
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
        } catch (e: Exception) {
            return false
        }
    }

    fun delete(id: Int): Boolean {
        try {
            val db = mDataBaseHelper.writableDatabase
            val selection = OPERATION.COLUMNS.ID + " = ? "
            val args = arrayOf(id.toString())
            db.delete(OPERATION.TABLE_NAME, selection, args)
            db.close()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun listAll(id: Int): List<OperationModel>? {
        var operationList: MutableList<OperationModel> = ArrayList()

        try {
            val db = mDataBaseHelper.readableDatabase
            val projection = arrayOf(
                OPERATION.COLUMNS.ID,
                OPERATION.COLUMNS.INPUT_POINT,
                OPERATION.COLUMNS.OUTPUT_POINT,
                OPERATION.COLUMNS.QUANTITY_CONTRACT,
                OPERATION.COLUMNS.RESULT_POINT,
                OPERATION.COLUMNS.RESULT_FINANCE
            )

            val cursor = db.query(
                OPERATION.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id: Int = cursor.getInt(cursor.getColumnIndex(OPERATION.COLUMNS.ID))
                    val inputPoint: Int =
                        cursor.getInt(cursor.getColumnIndex(OPERATION.COLUMNS.INPUT_POINT))
                    val outputPoint: Int =
                        cursor.getInt(cursor.getColumnIndex(OPERATION.COLUMNS.OUTPUT_POINT))
                    val quantityContract: Int =
                        cursor.getInt(cursor.getColumnIndex(OPERATION.COLUMNS.QUANTITY_CONTRACT))
                    val resultPoint: Int =
                        cursor.getInt(cursor.getColumnIndex(OPERATION.COLUMNS.RESULT_POINT))
                    val resultFinance: Double =
                        cursor.getDouble(cursor.getColumnIndex(OPERATION.COLUMNS.RESULT_FINANCE))

                    val operation = OperationModel(
                        id,
                        inputPoint,
                        outputPoint,
                        quantityContract,
                        resultPoint,
                        resultFinance
                    )
                    operationList.add(operation)
                }
            }
            cursor.close()
            db.close()
        } catch (e: Exception) {
            operationList
        }
        return operationList
    }
}