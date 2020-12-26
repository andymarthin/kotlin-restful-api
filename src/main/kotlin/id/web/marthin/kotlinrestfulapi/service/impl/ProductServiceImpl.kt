package id.web.marthin.kotlinrestfulapi.service.impl

import id.web.marthin.kotlinrestfulapi.entity.Product
import id.web.marthin.kotlinrestfulapi.error.NotFoundException
import id.web.marthin.kotlinrestfulapi.model.CreateProductRequest
import id.web.marthin.kotlinrestfulapi.model.ListProductRequest
import id.web.marthin.kotlinrestfulapi.model.ProductResponse
import id.web.marthin.kotlinrestfulapi.model.UpdateProductRequest
import id.web.marthin.kotlinrestfulapi.repository.ProductRepository
import id.web.marthin.kotlinrestfulapi.service.ProductService
import id.web.marthin.kotlinrestfulapi.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ProductServiceImpl(
        val productRepository: ProductRepository,
        val validationUtil: ValidationUtil
) : ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtil.validate(createProductRequest)

        val product = Product(
                id = createProductRequest.id,
                name = createProductRequest.name,
                price = createProductRequest.price,
                quantity = createProductRequest.quantity,
                createdAt = Date(),
                updatedAt = null
        )

        productRepository.save(product)

        return convertProductToResponse(product)
    }

    override fun get(id: String): ProductResponse {

        val product = findProductByIdOrThrowNotFound(id)

        return convertProductToResponse(product)

    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        validationUtil.validate(updateProductRequest)

        var product = findProductByIdOrThrowNotFound(id)

        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            updatedAt = Date()
        }

        productRepository.save(product)

        return convertProductToResponse(product)
    }

    override fun delete(id: String) {
        val product = findProductByIdOrThrowNotFound(id)
        productRepository.delete(product)
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        val products: List<Product> = page.get().collect(Collectors.toList())

        return products.map { convertProductToResponse(it) }
    }

    private fun findProductByIdOrThrowNotFound(id: String): Product{
        var product = productRepository.findByIdOrNull(id)
        if(product == null){
            throw NotFoundException()
        }
        return product
    }

    private fun convertProductToResponse(product: Product): ProductResponse{
        return ProductResponse(
                id = product.id,
                name = product.name,
                price = product.price,
                quantity = product.quantity,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt
        )
    }

}