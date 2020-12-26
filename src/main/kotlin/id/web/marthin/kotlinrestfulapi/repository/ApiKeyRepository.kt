package id.web.marthin.kotlinrestfulapi.repository

import id.web.marthin.kotlinrestfulapi.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository: JpaRepository<ApiKey, String> {
}