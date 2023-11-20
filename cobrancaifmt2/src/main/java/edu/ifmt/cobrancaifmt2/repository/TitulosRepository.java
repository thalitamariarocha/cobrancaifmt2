package edu.ifmt.cobrancaifmt2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.ifmt.cobrancaifmt2.model.titulo.Titulo;

public interface TitulosRepository extends JpaRepository<Titulo, Long> {
    
}
