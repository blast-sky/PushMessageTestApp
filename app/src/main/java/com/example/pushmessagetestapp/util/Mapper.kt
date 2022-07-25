package com.example.pushmessagetestapp.util

interface Mapper<in I, out O> {

    fun map(value: I): O

}