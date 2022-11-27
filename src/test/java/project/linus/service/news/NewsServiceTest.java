package project.linus.service.news;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.linus.model.news.News;
import project.linus.model.news.NewsManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewsServiceTest {

    @Autowired
    NewsService newsServiceTest;

//    @Test
//    public void agendarOperacaoQuandoValoresValidos() {
//        Banco bancoTest = new Banco();
//
//        ContaBancaria contaTest01 = new ContaBancaria(123, 100.0);
//
//        bancoTest.agendarOperacao("Débito", 10.0, contaTest01);
//
//        assertEquals("Débito", bancoTest.filaOperacao.peek().getTipoOperacao());
//        assertEquals(10.0, bancoTest.filaOperacao.peek().getValor());
//        assertEquals(contaTest01, bancoTest.filaOperacao.peek().getContaBancaria());
//    }

    @Test
    public void executarOperacoesAgendadasEsvaziaFila() {
        News news = new News();

        newsServiceTest.agendarOperacao(news);

        newsServiceTest.publicarNews();

        assertTrue(newsServiceTest.getFilaOperacao().isEmpty());
    }


}