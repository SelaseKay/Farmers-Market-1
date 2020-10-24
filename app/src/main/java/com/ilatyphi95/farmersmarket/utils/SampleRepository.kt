package com.ilatyphi95.farmersmarket.utils

import androidx.lifecycle.liveData
import com.ilatyphi95.farmersmarket.IRepository
import com.ilatyphi95.farmersmarket.ProductGenerator
import com.ilatyphi95.farmersmarket.data.entities.CloseByProduct
import com.ilatyphi95.farmersmarket.data.entities.Product
import com.ilatyphi95.farmersmarket.data.entities.User
import kotlinx.coroutines.delay
import kotlin.random.Random

class SampleRepository : IRepository {
    override fun searchProducts(searchString: String)  = liveData {
        delay(1000)
        ProductGenerator.resetList(10)
        emit(ProductGenerator.getList())
    }

    override suspend fun getUser(sellerId: String): User {
        delay(3000)
        return User(id = sellerId, email = "ilatyphi95@gmail.com", firstName = "AbdulLateef",
        lastName = "Opebiyi", phone = "08038057735", location = "Nigeria", profileDisplayName = "ilatyphi95")
    }

    override suspend fun getRecentProducts(): List<Product> {
        delay(1000)
        return ProductGenerator.resetList(15)
    }

    override suspend fun getCloseByProduct(): List<CloseByProduct> {
        val list = mutableListOf<CloseByProduct>()
        ProductGenerator.resetList(40).forEach {
            list.add(CloseByProduct(it, Random.nextInt(1, 40)))
        }
        return list.sortedBy { it.distance }
    }
}