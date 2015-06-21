package pl.edu.agh.soa.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by Ala Czyz.
 */
@Entity
@Table(name = "payment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Payment  implements Serializable{

    private static final long serialVersionUID = -4860796672110809073L;

    public Payment() {
    }

    public Payment(Date dueDate, BigDecimal grossCost,  Status status,Reservation reservation) {
        this.reservation = reservation;
        this.grossCost = grossCost;
        this.dueDate = dueDate;
        this.status = status;
    }

    @Id
    @GeneratedValue
    @Column(name = "pay_id")
    private Long id;


    @OneToOne
//    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "res_id")
    private Reservation reservation;


    @Column(name = "pay_gross_cost")
    private BigDecimal grossCost;

    @Column(name = "pay_due_date", nullable = false)
    private Date dueDate;

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "pay_status", nullable = false)
    Status status = Status.UNPAID;


    public Reservation getReservation() {
        return reservation;
    }

    public Status getStatus() {
        return status;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public BigDecimal getGrossCost() {
        return grossCost;
    }

    public void setGrossCost(BigDecimal grossCost) {
        this.grossCost = grossCost;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPaid() {
        return this != null && (status.equals(Status.PAID) || status.equals(Status.CANCELED));
    }

    public Long getUserId() {
        if(reservation != null) {
            return reservation.getAccount().id;
        }
        else return null;
    }

    public enum Status {
        UNPAID,
        PAID,
        PROCESSED,
        OVERDUE,
        CANCELED;
    }
}
