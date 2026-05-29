package com.school.roller_speed.model;

import jakarta.persistence.*;

@Entity
@Table(name = "horario_grupo")
public class HorarioGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horariogrupo")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_horario")
    private Horario horario;

    public HorarioGrupo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}
