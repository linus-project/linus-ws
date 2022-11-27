package project.linus.service.news;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.linus.model.login.AdminLogin;
import project.linus.model.news.News;
import project.linus.model.news.NewsManager;
import project.linus.model.user.User;
import project.linus.repository.NewsRepository;
import project.linus.repository.user.UserRepository;
import project.linus.service.content.ContentService;
import project.linus.service.login.LoginService;
import project.linus.util.exception.GenericException;
import project.linus.util.generic.FilaObj;
import project.linus.util.generic.ObjectList;
import project.linus.util.generic.PilhaObj;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    LoginService loginService;

    @Autowired
    UserRepository userRepository;

    private final String exportUrl = "~/Documents/git-projects/linus-ws/target/";

    FilaObj<News> filaOperacao = new FilaObj(10);

    private final Logger logger = LoggerFactory.logger(ContentService.class);

    public Optional<News> getNewsId(Integer idNews) {
        return newsRepository.findById(idNews);
    }

    public List<News> getNewsAll() {
        return newsRepository.findAll();
    }

    public boolean verifyIfNewsTitleExists(String newsTitle) {
        return newsRepository.findByNewsTitle(newsTitle).isPresent();
    }

    public News createNews(News newsM, Integer id) {
        News news = new News();

        User user = new User();
        user.setIdUser(id);

        if (userRepository.existsById(id)) {
            if (!verifyIfNewsTitleExists(newsM.getNewsTitle())) {
                news.setNewsTitle(newsM.getNewsTitle());
                news.setNews(newsM.getNews());
                agendarOperacao(news);
                news.setFkUser(user);
                newsRepository.save(news);
                publicarNews();
                return news;
            }
        }

        throw new GenericException();
    }

    public void agendarOperacao(News news) {
        filaOperacao.insert(news);
    }

    public void publicarNews() {
        while (!filaOperacao.isEmpty()) {
            filaOperacao.poll();
        }
    }

    public News editNews(NewsManager newsManager) {
        News news = newsRepository.findByIdNews(newsManager.getIdNews());

        AdminLogin admin = new AdminLogin(
                newsManager.getUsername(),
                newsManager.getPassword(),
                newsManager.getAdminkey());

        if (news != null && loginService.login(admin) != null) {
            if (newsManager.getNewsTitle() != null || !newsManager.getNewsTitle().equals("")) {
                news.setNewsTitle(newsManager.getNewsTitle());
            }
            if (newsManager.getNews() != null || !newsManager.getNews().equals("")) {
                news.setNews(newsManager.getNews());
            }
            newsRepository.save(news);
            return news;
        }
        throw new GenericException();
    }

    public News deleteNews(NewsManager newsManager) {
        News news = newsRepository.findByIdNews(newsManager.getIdNews());

        AdminLogin admin = new AdminLogin(
                newsManager.getUsername(),
                newsManager.getPassword(),
                newsManager.getAdminkey());

        if (news != null && loginService.login(admin) != null) {
            newsRepository.delete(news);
            return news;
        }
        throw new GenericException();
    }

    public List<News> bubbleSortByDate(List<News> newsList) {
        for (int firstIndex = 0; firstIndex < newsList.size(); firstIndex++) {
            for (int secondIndex = firstIndex + 1; secondIndex < newsList.size(); secondIndex++) {
                if (newsList.get(firstIndex).getIdNews() >
                        newsList.get(secondIndex).getIdNews()) {
                    News newsAux = newsList.get(firstIndex);
                    newsList.set(firstIndex, newsList.get(secondIndex));
                    newsList.set(secondIndex, newsAux);
                }
            }
        }
        return newsList;
    }

    public List<News> OrderById() {
        List<News> list = newsRepository.findAll();
        PilhaObj<News> pilha = new PilhaObj(list.size());

        for (int i = 0; !pilha.isFull(); i++) {
            pilha.push(list.get(i));
        }
        for (int i = 0; !pilha.isEmpty(); i++) {
            list.set(i, pilha.pop());
        }
        return list;
    }


    public News importNewsTxt(String fileTitle) {

        String registry, registryType;

        String newsTitle, news, name, userName, email, genero, numTelefone;
        Integer idUser, nivelUser;

        List<News> listaLidaNews = new ArrayList();
        List<User> listaLidaUsuarios = new ArrayList();

        int contaRegDadoLido = 0;
        Integer qtdRegDadoGravado;

        Scanner scanner = new Scanner(fileTitle);

        while (scanner.hasNext()) {
            registry = scanner.nextLine();
            registryType = registry.substring(0, 2);

            if (registryType.equals("00")) {
                System.out.println("Header Registry");
                System.out.println("File Type: " + registry.substring(2, 6));
                System.out.println("Recording Date and Time: " + registry.substring(6, 25));
                System.out.println("Document Version: " + registry.substring(25, 27));

            } else if (registryType.equals("01")) {
                System.out.println("Trailer Registration");
                qtdRegDadoGravado = Integer.valueOf(registry.substring(2, 12));

                if (contaRegDadoLido == qtdRegDadoGravado) {
                    logger.info("Number of records read is compatible with number of records written");
                } else {
                    logger.info("Number of records read is not compatible with number of records written");
                }

            } else if (registryType.equals("02")) {
                System.out.println("Corporation Registration");

                newsTitle = registry.substring(3, 24).trim();
                news = registry.substring(24, 695).trim();

                contaRegDadoLido++;

                News newNews = new News(null, newsTitle, news,null);
                listaLidaNews.add(newNews);

            } else if (registryType.equals("03")) {
                idUser = Integer.valueOf(registry.substring(2,3));
                name = registry.substring(3, 27).trim();
                userName = registry.substring(27, 47).trim();
                email = registry.substring(47, 82).trim();
                genero = registry.substring(82, 84).trim();
                numTelefone = registry.substring(84, 93).trim();
                nivelUser = Integer.valueOf(registry.substring(94, 95));

                contaRegDadoLido++;

                User newUser = new User(idUser, name, userName, email, genero, numTelefone,nivelUser);
                listaLidaUsuarios.add(newUser);

            } else {
                logger.info("[ERROR] - Invalid record type! ");
                System.out.println(registryType);
            }
        }

        System.out.println("\nConteúdo da lista lida do arquivo");
        for (int i = 0; i < listaLidaNews.size(); i++) {
            System.out.println(listaLidaNews.get(i));
            System.out.println(listaLidaUsuarios.get(i));
            News n = listaLidaNews.get(i);
            User u = listaLidaUsuarios.get(i);

            User newUser = new User();
            newUser.setIdUser(u.getIdUser());
            newUser.setIdUser(u.getIdUser());
            newUser.setName(u.getName());
            newUser.setUsername(u.getUsername());
            newUser.setEmail(u.getEmail());
            newUser.setGenre(u.getGenre());
            newUser.setPhoneNumber(u.getPhoneNumber());
            newUser.setFkLevel(u.getFkLevel());

            if (userRepository.existsById(newUser.getIdUser())){
                News newNews = new News();
                newNews.setNewsTitle(n.getNewsTitle());
                newNews.setNews(n.getNews());
                newNews.setFkUser(newUser);
                agendarOperacao(newNews);
                newsRepository.save(newNews);
                publicarNews();
                return newNews;
            }
        }
        throw new GenericException();
    }

    public String exportNews(Integer listSize, String fileTitle) {

        FileWriter file = null;
        Formatter formatter = null;
        fileTitle += ".txt";

        ObjectList<News> newsList = new ObjectList(listSize);

        int index = 0;

        for (News news : newsRepository.findAll()) {
            newsList.add(news);
            if (index == listSize - 1) break;
            index++;
        }

        try {
            file = new FileWriter(fileTitle);
            formatter = new Formatter(file);
        } catch (IOException error) {
            logger.info("[ERROR] - exportNews: " + error);
        }

        try {
            String header = "00NEWS";
            header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            header += "00";
            formatter.format(header + "\n");

            String body;
            for (index = 0; index < newsList.getSize(); index++) {
                News news = newsList.getElement(index);
                body = "02";
                body += String.format("%-5.5s", news.getIdNews());
                body += String.format("%-22.22s", news.getNewsTitle());
                body += String.format("%-671.671s", news.getNews());

                formatter.format(body + "\n");
            }
            String trailer = "01";
            trailer += String.format("%010d", index);
            formatter.format(trailer + "\n");

        } catch (FormatterClosedException error) {
            logger.info("[ERROR] - exportUser: " + error);
        } finally {
            formatter.close();
            try {
                file.close();
            } catch (IOException error) {
                logger.info("[ERROR] - exportUser: " + error);
            }
        }
        logger.info("The file " + fileTitle + " has been exported successfully!");
        return exportUrl + fileTitle;
    }

    public FilaObj<News> getFilaOperacao() {
        return filaOperacao;
    }

}
