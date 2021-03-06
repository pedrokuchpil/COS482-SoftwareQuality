package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Emprestimo} entity.
 */
public class EmprestimoDTO implements Serializable {

    private Long id;

    private LocalDate date;

    private BookDTO book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmprestimoDTO)) {
            return false;
        }

        EmprestimoDTO emprestimoDTO = (EmprestimoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, emprestimoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmprestimoDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", book=" + getBook() +
            "}";
    }
}
