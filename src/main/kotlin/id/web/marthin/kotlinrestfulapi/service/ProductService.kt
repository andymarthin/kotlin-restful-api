package id.web.marthin.kotlinrestfulapi.service

import id.web.marthin.kotlinrestfulapi.model.CreateProductRequest
import id.web.marthin.kotlinrestfulapi.model.ListProductRequest
import id.web.marthin.kotlinrestfulapi.model.ProductResponse
import id.web.marthin.kotlinrestfulapi.model.UpdateProductRequest

interface ProductService {

    fun create(createProductRequest: CreateProductRequest): ProductResponse

    fun get(id: String): ProductResponse

    fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse

    fun delete(id: String)

    fun list(listProductRequest: ListProductRequest): List<ProductResponse>
}