package com.school.roller_speed.controllers;

import com.school.roller_speed.models.ClassGroup;
import com.school.roller_speed.models.Schedule;
import com.school.roller_speed.models.Teacher;
import com.school.roller_speed.services.GroupService;
import com.school.roller_speed.services.ScheduleService;
import com.school.roller_speed.services.StudentService;
import com.school.roller_speed.services.TeacherService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GroupController {
    private final GroupService groupService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ScheduleService scheduleService;

    public GroupController(
            GroupService groupService,
            TeacherService teacherService,
            StudentService studentService, ScheduleService scheduleService) {
        this.groupService = groupService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.scheduleService = scheduleService;
    }
    /*==============================================*/
    @GetMapping("/grupos")
    public String groupsPage(Model model) {
        model.addAttribute("groups", groupService.getAllGroups());
        model.addAttribute("totalGroups",
                groupService.getTotalGroups());
        model.addAttribute("totalTeachers",
                teacherService.getTotalTeachers());
        model.addAttribute("totalStudents",
                studentService.getTotalStudents());

        return "admin/grupos";
    }
    /*==============================================*/
    @GetMapping("/grupos/agregar")
    public String getAddGroup(Model model) {
         model.addAttribute("group", new ClassGroup());
        List<Teacher> teacherList = teacherService.getAllTeachers();
        List<Schedule> scheduleList = scheduleService.getAllSchedules();
        model.addAttribute("teachers",teacherList);
        model.addAttribute("schedules",scheduleList);
             
        return "admin/agregar_grupo";
    }
    /*==============================================*/
    @PostMapping("/grupos/guardar")
    public String saveGroup(@ModelAttribute ClassGroup group) {
        groupService.saveGroup(group);

        return "redirect:/grupos";
    }
    /*==============================================*/
    @GetMapping("/grupos/editar/{groupId}")
    public String getEditGroup(@PathVariable Integer groupId,Model model) {

        ClassGroup groupById = groupService.getGroupById(groupId);
        model.addAttribute("groupById",groupById);

        List<Teacher> teacherList = teacherService.getAllTeachers();
        List<Schedule> scheduleList = scheduleService.getAllSchedules();
        model.addAttribute("teachers",teacherList);
        model.addAttribute("schedules",scheduleList);

        return "admin/editar_grupo";
    }
    /*==============================================*/
    @PostMapping("/grupos/actualizar")
    public String putGroup(@ModelAttribute ClassGroup group) {
        groupService.updateGroup(group);

        return "redirect:/grupos";
    }
    /*==============================================*/
    @GetMapping("/grupos/eliminar/{groupId}")
    public String putGroup(@PathVariable Integer groupId) {
        groupService.deleteGroup(groupId);
        return "redirect:/grupos";
    }
}