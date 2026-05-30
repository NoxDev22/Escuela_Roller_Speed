package com.school.roller_speed.services;
import com.school.roller_speed.models.ClassGroup;
import com.school.roller_speed.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    //=================================================================
    public List<ClassGroup> getAllGroups() {
        return groupRepository.findAll();
    }
    //=================================================================
    public long getTotalGroups() {
        return groupRepository.count();
    }
    //=================================================================
    public void saveGroup(ClassGroup group) {
    groupRepository.save(group);
    }
    //=================================================================
    // BUSCAR GRUPO POR ID
    public ClassGroup getGroupById(Integer groupId){
        Optional<ClassGroup> optional =
    groupRepository.findById(groupId);
    return optional.orElseThrow(()-> new RuntimeException("Grupo no encontrado "+groupId));
    }
    //=================================================================
    // Actualizar Grupo
    public void updateGroup(ClassGroup groupUpdated){
    ClassGroup group = groupRepository.findById(groupUpdated.getGroupId())
            .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

    group.setGroupName(groupUpdated.getGroupName());
    group.setGroupLevel(groupUpdated.getGroupLevel());
    group.setTeacher(groupUpdated.getTeacher());
    group.setSchedule(groupUpdated.getSchedule());
    groupRepository.save(group);
    }
    //=================================================================
    // ELIMINAR GRUPO
    public void deleteGroup(Integer id) {
    groupRepository.deleteById(id);
    }
}