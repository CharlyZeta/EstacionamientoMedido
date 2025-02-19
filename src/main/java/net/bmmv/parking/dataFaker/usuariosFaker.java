package net.bmmv.parking.dataFaker;

import com.github.javafaker.Faker;
import net.bmmv.parking.model.Usuario;
import net.bmmv.parking.service.IServiceUsuario;
import net.bmmv.parking.service.ServiceUsuario;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@Component
public class usuariosFaker {
    @Autowired
    private IServiceUsuario serviceUsuario;
    public List<Usuario> generateUsuarios(int numUsuarios) {
        List<Usuario> usuarios = new ArrayList<>();
        Faker faker = new Faker(Locale.forLanguageTag("es-AR"));
        Logger logger = LoggerFactory.getLogger(usuariosFaker.class);

        if(numUsuarios>0){
            for (int i = 0; i < numUsuarios; i++) {
                Usuario usuario = generateUser(faker);
                usuarios.add(usuario);
                serviceUsuario.guardarUsuario(usuario);
            }
        }
        logger.info("Creando Usuarios en memoria");
        usuarios.forEach(l -> logger.info(l.toString()));
        return usuarios;
    }
    private Usuario generateUser(Faker faker) {
        Usuario usuario = new Usuario();
        long min = 3000000L, max = 48000000L;
        long randomNumber = ThreadLocalRandom.current().nextLong(min, max + 1);
        usuario.setDni(randomNumber);
        usuario.setNombre(faker.name().firstName());
        usuario.setApellido(faker.name().lastName());
        usuario.setDomicilio(generateDomicilio(faker));
        String name = usuario.getNombre().substring(0,1).toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        String lastname = usuario.getApellido().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        usuario.setEmail(generateEmail(name,lastname,faker.random().nextInt(1, 50)));
        usuario.setFecha_nacimiento(generateFechaNacimiento(faker));
        usuario.setPatente(generatePatente());
        usuario.setContrasena(generateContrasena(faker));
        usuario.setSaldo_cuenta(generateSaldoCuenta());
        return usuario;
    }

    /*
    * Cargamos todos los usuarios generados en la base de datos en memoria
    *
     */
//    private static void CargarUsariosBase(List<Usuario> usuarios) {
//       Logger logger = LoggerFactory.getLogger(usuariosFaker.class);
//       usuarios.forEach(serviceUsuario::guardarUsuario);
//       logger.info("----> Usuarios cargados en memoria");
//    }

    private static long generateDNI() {
        Random random = new Random();
        int digits = random.nextInt(2) + 7; // Genera un número entre 7 y 8 dígitos
        return random.nextInt((int) Math.pow(10, digits));
    }

    private static String generateDomicilio(Faker faker) {
        return faker.address().streetAddress() + ", Santa Fe, Argentina";
    }

    private static String generateEmail(String nombre, String apellido, int numero) {
        return nombre.toLowerCase() + apellido.toLowerCase() + numero + "@example.com";
    }

    private static LocalDate generateFechaNacimiento(Faker faker) {
        return faker.date().birthday(18, 50).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static String generatePatente() {
        Random random = new Random();
        int length = random.nextBoolean() ? 7 : 9;
        StringBuilder patente = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i < length - 4) {
                patente.append((char) ('A' + random.nextInt(26)));
            } else {
                patente.append(random.nextInt(10));
            }
        }
        return patente.toString();
    }

    private static String generateContrasena(Faker faker) {
        return faker.internet().password(6, 20);
    }

    private static float generateSaldoCuenta() {
        Random random = new Random();
        return (float) random.nextDouble(0, 100000.00);
    }
}
