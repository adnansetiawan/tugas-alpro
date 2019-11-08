package tugasalpro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     * @throws URISyntaxException
     * @throws IOException
     */
    public static void main(String[] args) {
        
        //RegistrasiPengguna registrasiPengguna = new RegistrasiPengguna();
        //registrasiPengguna.Registrasi();
        UserManager userManager = new UserManager();
        List<User> users = userManager.GetAll();
    }
}
