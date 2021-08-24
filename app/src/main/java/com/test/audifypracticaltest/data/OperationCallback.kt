package com.test.audifypracticaltest.data


interface OperationCallback<T> {
    fun onSuccess(data: List<T>?)
    fun onError(error: String?)
}