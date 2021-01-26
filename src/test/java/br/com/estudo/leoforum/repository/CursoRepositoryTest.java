package br.com.estudo.leoforum.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.estudo.leoforum.modelo.Curso;

@RunWith(SpringRunner.class)
@DataJpaTest
class CursoRepositoryTest {
	
//	@Autowired
//	private CursoRepository cursoRepository;
//
//	@Test
//	public void carregarCursoAoBuscarPeloNome() {
//		
//		String nomeCurso = "React";
//		
//		Curso curso = cursoRepository.findByNome(nomeCurso);
//		
//		Assert.assertNotNull(curso);
//		
//		Assert.assertEquals(nomeCurso, curso.getNome());
//		
//	}

}
