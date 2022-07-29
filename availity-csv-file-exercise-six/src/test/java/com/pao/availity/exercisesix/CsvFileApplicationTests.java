package com.pao.availity.exercisesix;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pao.availity.exercisesix.controller.FileContentController;

@SpringBootTest
class CsvFileApplicationTests {

	@Autowired
	private FileContentController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
