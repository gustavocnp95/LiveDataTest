package com.medium.livedatatest;

import com.medium.livedatatest.conta.ContaEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ContaCadastro {
    private String titulo;
    private String tipo;
    private String valor;
    private String dataVencimento;

    public ContaEntity toContaEntity() {
        return new ContaEntity(
                titulo,
                tipo,
                valor != null ? Double.parseDouble(valor) : 0,
                getDataVencimentoAsDate());
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataVencimentoAsDate() {
        try {
            return dataVencimento != null ? new SimpleDateFormat("dd/MM/yyyy").parse(dataVencimento) : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
