package com.example.myapplication.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
