package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EmprestimoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmprestimoDTO.class);
        EmprestimoDTO emprestimoDTO1 = new EmprestimoDTO();
        emprestimoDTO1.setId(1L);
        EmprestimoDTO emprestimoDTO2 = new EmprestimoDTO();
        assertThat(emprestimoDTO1).isNotEqualTo(emprestimoDTO2);
        emprestimoDTO2.setId(emprestimoDTO1.getId());
        assertThat(emprestimoDTO1).isEqualTo(emprestimoDTO2);
        emprestimoDTO2.setId(2L);
        assertThat(emprestimoDTO1).isNotEqualTo(emprestimoDTO2);
        emprestimoDTO1.setId(null);
        assertThat(emprestimoDTO1).isNotEqualTo(emprestimoDTO2);
    }
}
