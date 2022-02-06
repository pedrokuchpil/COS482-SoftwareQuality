package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Emprestimo.
 */
@Entity
@Table(name = "emprestimo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    private Usuario receiver;

    @ManyToOne
    @JsonIgnoreProperties(value = { "owner" }, allowSetters = true)
    private Book book;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Emprestimo id(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Emprestimo date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Usuario getReceiver() {
        return this.receiver;
    }

    public Emprestimo receiver(Usuario Usuario) {
        this.setReceiver(Usuario);
        return this;
    }

    public void setReceiver(Usuario Usuario) {
        this.receiver = Usuario;
    }

    public Book getBook() {
        return this.book;
    }

    public Emprestimo book(Book Book) {
        this.setBook(Book);
        return this;
    }

    public void setBook(Book Book) {
        this.book = Book;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Emprestimo)) {
            return false;
        }
        return id != null && id.equals(((Emprestimo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Emprestimo{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            "}";
    }
}
