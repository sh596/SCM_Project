package com.example.dudeulimproject.view.info_modify.repository

import com.example.dudeulimproject.data.remote.MainService
import javax.inject.Inject

class InfoModifyRepositoryImpl @Inject constructor(private val service: MainService) : InfoModifyRepository{
    override suspend fun save(){

    }
}