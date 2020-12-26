package id.web.marthin.kotlinrestfulapi.controller

import id.web.marthin.kotlinrestfulapi.model.*
import id.web.marthin.kotlinrestfulapi.service.ProductService
import org.springframework.data.jpa.repository.Query
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(val productService: ProductService) {

    @PostMapping(
            value = ["/api/products"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductResponse>{
        val productResponse = productService.create(body)

        return WebResponse(
                code = 200,
                status = "Ok",
                data = productResponse
        )
    }

    @GetMapping(
            value = ["/api/products/{idProduct}"],
            produces = ["application/json"]
    )
    fun getProduct(@PathVariable("idProduct") id: String): WebResponse<ProductResponse>{
        val productResponse = productService.get(id)
        return WebResponse(
                code = 200,
                status = "Ok",
                data = productResponse
        )
    }

    @PutMapping(
            value = ["/api/products/{idProduct}"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun updateProduct(@PathVariable("idProduct") id: String, @RequestBody updateProductRequest: UpdateProductRequest): WebResponse<ProductResponse>{
        var productResponse = productService.update(id, updateProductRequest)
        return WebResponse(
                code = 200,
                status = "Ok",
                data = productResponse
        )
    }

    @DeleteMapping(
            value = ["/api/products/{idProduct}"],
            produces = ["application/json"]
    )
    fun deleteProduct(@PathVariable("idProduct") id: String): WebResponse<String>{
        productService.delete(id)

        return WebResponse(
                code = 200,
                status = "Ok",
                data = id
        )
    }

    @GetMapping(
            value = ["/api/products"],
            produces = ["application/json"]
    )
    fun listProducts(@RequestParam("size", defaultValue = "10") size: Int,
                     @RequestParam("page", defaultValue = "0") page: Int):
            WebResponse<List<ProductResponse>>{
        val request = ListProductRequest(size = size,page = page)
        val response = productService.list(request)
        return WebResponse(
                code = 200,
                status = "Ok",
                data = response
        )
    }
}