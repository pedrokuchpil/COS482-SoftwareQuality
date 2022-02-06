package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EmprestimoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Emprestimo.class);
        Emprestimo emprestimo1 = new Emprestimo();
        emprestimo1.setId(1L);
        Emprestimo emprestimo2 = new Emprestimo();
        emprestimo2.setId(emprestimo1.getId());
        assertThat(emprestimo1).isEqualTo(emprestimo2);
        emprestimo2.setId(2L);
        assertThat(emprestimo1).isNotEqualTo(emprestimo2);
        emprestimo1.setId(null);
        assertThat(emprestimo1).isNotEqualTo(emprestimo2);
    }
}
