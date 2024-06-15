package com.binhlc.adminweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detail_transaction", schema = "movie_ticket")
public class DetailTransactionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_transaction")
    private Integer idTransaction;

    @Column(name = "id_ticket")
    private Integer idTicket;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }
}
