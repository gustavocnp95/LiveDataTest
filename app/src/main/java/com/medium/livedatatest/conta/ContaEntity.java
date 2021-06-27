package com.medium.livedatatest.conta;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class ContaEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Long id;
    @ColumnInfo(name = "title")
    private String titulo;
    @ColumnInfo(name = "type")
    private String tipoConta;
    @ColumnInfo(name = "amount")
    private Double valor;
    @ColumnInfo(name = "due_date")
    private Long dataVencimento;

    public ContaEntity() {
    }

    public ContaEntity(@NonNull final String titulo,
                       @NonNull final String tipoConta,
                       @NonNull final Double valor,
                       @NonNull final Date dataVencimento) {
        this.titulo = titulo;
        this.tipoConta = tipoConta;
        this.valor = valor;
        this.dataVencimento = dataVencimento.getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Long dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento.getTime();
    }
}
