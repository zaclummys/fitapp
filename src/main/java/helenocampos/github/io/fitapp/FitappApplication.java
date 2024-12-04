package helenocampos.github.io.fitapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Classe principal da aplicação FitApp.
 * Responsável por inicializar o Spring Boot.
 */
@SpringBootApplication
public class FitappApplication {

    /**
    * Método principal que inicia a aplicação.
    *
    * @param args Argumentos da linha de comando.
    */
    public static void main(String[] args) {
        SpringApplication.run(FitappApplication.class, args);
    }

}
