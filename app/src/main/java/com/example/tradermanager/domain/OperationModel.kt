package com.example.tradermanager.domain

data class OperationModel (
        var id: Int = 0,
        val inputPoint: Int,
        val outputPoint: Int,
        val quantityContract: Int,
        val resultPoint: Int,
        val resultFinance: Double)