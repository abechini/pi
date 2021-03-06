package com.esprit.bankPi.data;

import javax.persistence.Table;


import com.esprit.bankPi.model.DayPlan;
import com.esprit.bankPi.model.TimePeroid;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalTime;

@TypeDefs(@TypeDef(name = "json", typeClass = JsonStringType.class))
@Table(name = "data_WorkingPlan")
@javax.persistence.Entity(name = "data_WorkingPlan")
public class WorkingPlan {
	    public Agent getAgent() {
		return agent;
	}

	public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}


		@Id
	    @Column(name = "id_working_plan")
	    private Long id;

	    @MapsId
	    @OneToOne
	    @JoinColumn(name = "id_agent")
	    private Agent agent;

	    @Type(type = "json")
	    @Column(columnDefinition = "json", name = "monday")
	    private DayPlan monday;

	    @Type(type = "json")
	    @Column(columnDefinition = "json", name = "tuesday")
	    private DayPlan tuesday;

	    @Type(type = "json")
	    @Column(columnDefinition = "json", name = "wednesday")
	    private DayPlan wednesday;

	    @Type(type = "json")
	    @Column(columnDefinition = "json", name = "thursday")
	    private DayPlan thursday;

	    @Type(type = "json")
	    @Column(columnDefinition = "json", name = "friday")
	    private DayPlan friday;

	    @Type(type = "json")
	    @Column(columnDefinition = "json", name = "saturday")
	    private DayPlan saturday;

	    @Type(type = "json")
	    @Column(columnDefinition = "json", name = "sunday")
	    private DayPlan sunday;


	    public WorkingPlan() {
	    }


	 

	    public DayPlan getDay(String day) {
	        switch (day) {
	            case "monday":
	                return monday;

	            case "tuesday":
	                return tuesday;

	            case "wednesday":
	                return wednesday;

	            case "thursday":
	                return thursday;

	            case "friday":
	                return friday;

	            case "saturday":
	                return saturday;

	            case "sunday":
	                return sunday;

	            default:
	                return null;
	        }
	    }

	    public DayPlan getMonday() {
	        return monday;
	    }

	    public void setMonday(DayPlan monday) {
	        this.monday = monday;
	    }

	    public DayPlan getTuesday() {
	        return tuesday;
	    }

	    public void setTuesday(DayPlan tuesday) {
	        this.tuesday = tuesday;
	    }

	    public DayPlan getWednesday() {
	        return wednesday;
	    }

	    public void setWednesday(DayPlan wednesday) {
	        this.wednesday = wednesday;
	    }

	    public DayPlan getThursday() {
	        return thursday;
	    }

	    public void setThursday(DayPlan thursday) {
	        this.thursday = thursday;
	    }

	    public DayPlan getFriday() {
	        return friday;
	    }

	    public void setFriday(DayPlan friday) {
	        this.friday = friday;
	    }

	    public DayPlan getSaturday() {
	        return saturday;
	    }

	    public void setSaturday(DayPlan saturday) {
	        this.saturday = saturday;
	    }

	    public DayPlan getSunday() {
	        return sunday;
	    }

	    public void setSunday(DayPlan sunday) {
	        this.sunday = sunday;
	    }


	    public static WorkingPlan generateDefaultWorkingPlan() {
	        WorkingPlan wp = new WorkingPlan();
	        LocalTime defaultStartHour = LocalTime.parse("06:00");
	        LocalTime defaultEndHour = LocalTime.parse("18:00");
	        TimePeroid defaultWorkingPeroid = new TimePeroid(defaultStartHour, defaultEndHour);
	        DayPlan defaultDayPlan = new DayPlan(defaultWorkingPeroid);
	        wp.setMonday(defaultDayPlan);
	        wp.setTuesday(defaultDayPlan);
	        wp.setWednesday(defaultDayPlan);
	        wp.setThursday(defaultDayPlan);
	        wp.setFriday(defaultDayPlan);
	        wp.setSaturday(defaultDayPlan);
	        wp.setSunday(defaultDayPlan);
	        return wp;
	    }

}
