package com.TABOO.Backend;

import com.TABOO.Backend.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<Usuario> findByUsuario(String username);

    Optional<Object> findByUsername(String username);
}
