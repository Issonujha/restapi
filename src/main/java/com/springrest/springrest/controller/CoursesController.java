package com.springrest.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.services.CourseService;

import ch.qos.logback.core.status.Status;

@RestController
public class CoursesController {
	
	@Autowired
	private CourseService courseService;
	
	
	@GetMapping("/home")
	public String home() {
		return "Welcome to our Cources";
	}
	
	
	//For getting all the courses
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return courseService.getCourses();
	}
	
	
	@GetMapping("/courses/{courseId}")
	public Course getCourse(@PathVariable String courseId) {
		return courseService.getCourse(Long.parseLong(courseId));
	}
	
	
	@PostMapping("/courses")
	public Course addCourses(@RequestBody Course course) {
		return this.courseService.addCourse(course);
	}
	
	@PutMapping("/courses")
	public Course updateCourses(@RequestBody Course course) {
		return this.courseService.updateCourse(course);
	}
	
	
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<Status> deleteCourse(@PathVariable String courseId) {
		try {
			courseService.deleteCourse(Long.parseLong(courseId));
			return new ResponseEntity<Status>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Status>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
