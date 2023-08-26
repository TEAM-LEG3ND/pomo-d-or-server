package com.leg3nd.api.config

import io.ktor.server.application.*
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import com.leg3nd.domain.config.KoinConfig as DomainKoinConfig
import com.leg3nd.infrastructure.config.KoinConfig as InfraKoinConfig

@Module
@ComponentScan("com.leg3nd")
class KoinConfig

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(KoinConfig().module, DomainKoinConfig().module, InfraKoinConfig().module)
    }
}
