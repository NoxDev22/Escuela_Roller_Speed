package com.school.roller_speed.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long id;

    @Column(name = "nombre_grupo")
    private String nombreGrupo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_docente")
    private Docente docente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_horario")
    private Horario horario;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HorarioGrupo> horarioGrupos = new HashSet<>();

    public Grupo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Set<HorarioGrupo> getHorarioGrupos() {
        return horarioGrupos;
    }

    public void setHorarioGrupos(Set<HorarioGrupo> horarioGrupos) {
        this.horarioGrupos = horarioGrupos;
    }
}
