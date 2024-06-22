package net.bmmv.parking.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@Configuration
@EnableHypermediaSupport(type= EnableHypermediaSupport.HypermediaType.HAL) //el tipo HAL nos brinda mas información
public class HateoasConfig {

}
