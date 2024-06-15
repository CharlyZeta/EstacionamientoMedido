package net.bmmv.parking.dataFaker;

import com.github.javafaker.Faker;
import net.bmmv.parking.model.Usuario;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class usuariosFaker {

    public static List<Usuario> generateUsuarios(int numUsuarios) {
        Faker faker = new Faker(Locale.forLanguageTag("es-AR"));
        List<Usuario> usuarios = new ArrayList<>();
        Logger logger = LoggerFactory.getLogger(usuariosFaker.class);

        if(numUsuarios>0){
            for (int i = 0; i < numUsuarios; i++) {
                Usuario usuario = new Usuario();

                usuario.setDni(generateDNI());
                usuario.setNombre(faker.name().firstName());

                usuario.setApellido(faker.name().lastName());
                usuario.setDomicilio(generateDomicilio(faker));
                usuario.setEmail(generateEmail(usuario.getNombre(), usuario.getApellido(), faker.random().nextInt(1, 100)));
                usuario.setFecha_nacimiento(generateFechaNacimiento(faker));
                usuario.setPatente(generatePatente());
                usuario.setContrasena(generateContrasena(faker));
                usuario.setSaldo_cuenta(generateSaldoCuenta());
                usuarios.add(usuario);

            }
        }
        usuarios.forEach(l -> logger.info(l.toString()));
        return usuarios;
    }

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
