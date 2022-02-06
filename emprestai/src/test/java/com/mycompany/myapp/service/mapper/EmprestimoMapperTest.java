package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmprestimoMapperTest {

    private EmprestimoMapper emprestimoMapper;

    @BeforeEach
    public void setUp() {
        emprestimoMapper = new EmprestimoMapperImpl();
    }
}
