package com.example.exerciseapplication.data

sealed class InsertResult {
    data object Success : InsertResult()
    data object DuplicateDate : InsertResult()
    data class Failure(val throwable: Throwable) : InsertResult()
}
