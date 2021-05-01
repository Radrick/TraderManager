package com.example.tradermanager.service.constants

class OperationDatabaseConstants {

    companion object OPERATION{

        const val TABLE_NAME = "TraderManager"

        object COLUMNS {

            const val ID = "id"
            const val INPUT_POINT = "inputPoint"
            const val OUTPUT_POINT = "outputPoint"
            const val QUANTITY_CONTRACT = "quantityContract"
            const val RESULT_POINT = "resultPoint"
            const val RESULT_FINANCE = "resultFinance"
            
        }
    }

}