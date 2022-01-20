package com.nlt.app.model.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="projects")
public class Project extends BaseEntity {

    // •	id – integer number, primary identification field.
    //•	name – a string (required).
    @Column(name="name", nullable = false)
    private String name;
    //•	description – a very long text description (required).
    @Column(name="description", nullable = false)
    private String description;
    //•	is finished – a Boolean.
    @Column(name="is_finished")
    private boolean finished;
    //•	payment – Big Decimal (required).
    @Column(name="payment", nullable = false)
    private BigDecimal payment;
    //•	start date – a date.
    @Column(name="date")
    private LocalDate date;
    //•	company – a Company entity (required).
    @ManyToOne
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private Company company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Project name: %s%n",name));
        sb.append(String.format("\tDescription: %s%n",description));
        sb.append(String.format("\t%s",payment));
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
