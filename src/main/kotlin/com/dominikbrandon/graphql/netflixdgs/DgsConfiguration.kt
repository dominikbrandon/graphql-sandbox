package com.dominikbrandon.graphql.netflixdgs

import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.netflix.graphql.dgs.webmvc.autoconfigure.DgsWebMvcAutoconfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile

@Configuration
@Profile("netflix-dgs")
@Import(DgsAutoConfiguration::class, DgsWebMvcAutoconfiguration::class)
class DgsConfiguration
