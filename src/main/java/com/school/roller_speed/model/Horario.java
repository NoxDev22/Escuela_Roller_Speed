package com.school.roller_speed.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "horarios_clases")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Long id;

    @Column(name = "dia")
    private String dia;

    @Column(name = "hora")
    private String hora;

    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HorarioGrupo> horarioGrupos = new HashSet<>();

    public Horario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Set<HorarioGrupo> getHorarioGrupos() {
        return horarioGrupos;
    }

    public void setHorarioGrupos(Set<HorarioGrupo> horarioGrupos) {
        this.horarioGrupos = horarioGrupos;
    }
}
