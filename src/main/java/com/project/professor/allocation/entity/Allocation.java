package com.project.professor.allocation.entity;

import java.time.DayOfWeek;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Allocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private DayOfWeek day;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date start;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date end;
	
	@Column(nullable = false, name = "Course_id")
	private Long courseId;
	
	@Column(nullable = false, name = "Professor_id")
	private Long professorId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "Course_id", updatable = false, insertable = false, nullable = false)
	private Course course;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "Professor_id", updatable = false, insertable = false, nullable = false)
	private Professor pro;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Professor getPro() {
		return pro;
	}

	public void setPro(Professor pro) {
		this.pro = pro;
	}

	@Override
	public String toString() {
		return "Allocation [id=" + id + ", day=" + day + ", start=" + start + ", end=" + end + ", courseId=" + courseId
				+ ", professorId=" + professorId + ", course=" + course + ", pro=" + pro + "]";
	}
	
	
}