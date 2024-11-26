package alatoo.softwareEngineering.FilmVista.controller.actor;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/actor")
@Tag(name = "Actor", description = "Actor operations")
public sealed interface ActorControllerDocumentation permits ActorController {

}
