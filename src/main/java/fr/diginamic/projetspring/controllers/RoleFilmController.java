package fr.diginamic.projetspring.controllers;

import fr.diginamic.projetspring.entities.RoleFilm;
import fr.diginamic.projetspring.services.RoleFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des rôles dans les films.
 */
@RestController
@RequestMapping("/roles")
public class RoleFilmController {

    /** Service gérant la logique métier des rôles dans les films. */
    private final RoleFilmService roleFilmService;

    /**
     * Constructeur du contrôleur avec injection du service.
     *
     * @param roleFilmService Service gérant la logique métier des rôles dans les films.
     */
    @Autowired
    public RoleFilmController(RoleFilmService roleFilmService) {
        this.roleFilmService = roleFilmService;
    }

    /**
     * Endpoint pour obtenir un rôle par son identifiant.
     *
     * @param id Identifiant du rôle à récupérer.
     * @return Le rôle correspondant à l'identifiant.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RoleFilm> getRoleById(@PathVariable("id") Long id) {
        Optional<RoleFilm> role = roleFilmService.getRoleById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint pour obtenir la liste de tous les rôles.
     *
     * @return La liste de tous les rôles.
     */
    @GetMapping
    public List<RoleFilm> getAllRoles() {
        return roleFilmService.getAllRoles();
    }

    /**
     * Endpoint pour créer un nouveau rôle.
     *
     * @param role Le rôle à créer.
     * @return Le rôle créé.
     */
    @PostMapping
    public ResponseEntity<RoleFilm> saveRoleFilm(@RequestBody RoleFilm role) {
        RoleFilm savedRole = roleFilmService.saveRoleFilm(role);
        return ResponseEntity.ok(savedRole);
    }

    /**
     * Endpoint pour supprimer un rôle par son identifiant.
     *
     * @param id Identifiant du rôle à supprimer.
     * @return Réponse indiquant le succès de l'opération.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id) {
        roleFilmService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    // Ajoutez d'autres méthodes d'endpoint au besoin
}