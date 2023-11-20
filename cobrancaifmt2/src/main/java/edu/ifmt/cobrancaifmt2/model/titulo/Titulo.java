package edu.ifmt.cobrancaifmt2.model.titulo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.*;

@Entity
public class Titulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 60, message = "A descrição não pode conter mais de 60 caracteres")
    private String descricao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data de vencimento é obrigatória")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    @NotNull(message = "Valor é obrigatório")
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private StatusTitulo status;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Date getDataVencimento() {

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // transforma a data em string, depois em LocalDate
        //String dataVencimentoString = dataVencimento.toString();
        //LocalDate localDate = LocalDate.parse(dataVencimentoString);
        return  dataVencimento;




    }

    public void setDataVencimento(Date dataVencimento) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //LocalDate localDate = LocalDate.parse(dataVencimento, formatter);
        this.dataVencimento = dataVencimento;//java.sql.Date.valueOf(localDate);
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public StatusTitulo getStatus() {
        return status;
    }
    public void setStatus(StatusTitulo status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Titulo other = (Titulo) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Titulo [codigo=" + codigo + ", descricao=" + descricao + ", dataVencimento=" + dataVencimento
                + ", valor=" + valor + ", status=" + status + "]";
    }

    public boolean isPendente() {
        return StatusTitulo.PENDENTE.equals(this.status);
    }
}
