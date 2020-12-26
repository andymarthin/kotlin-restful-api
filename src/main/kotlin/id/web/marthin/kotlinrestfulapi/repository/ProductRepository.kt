package id.web.marthin.kotlinrestfulapi.repository

import id.web.marthin.kotlinrestfulapi.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String> {
}