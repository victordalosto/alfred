package alfred.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import alfred.models.AlfredBackpack;


public class ZipDirectory {

    private static final String ALGORITHM = "AES";
    // private static final byte[] KEY = "passwordpasswordpassword".getBytes();


    public static void executa(String chave) {
        byte [] KEY = Hash.generateHash(chave);
        AlfredBackpack.getDestinyDirectories().forEach(dir -> {
            try {
                String output = DirectoryService.getValidName(dir + ".zip");
                zipDirectory(new File(dir), new File(output), KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }



    private static void zipDirectory(File directory, File zipFile, byte[] KEY) throws Exception {
        FileOutputStream fos = new FileOutputStream(zipFile);
        SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        try (ZipOutputStream zos = new ZipOutputStream(new CipherOutputStream(fos, cipher))) {
            zip(directory, directory, zos);
        }
        System.out.println("Arquivo zipado: " + zipFile);
    }



    private static void zip(File directory, File base, ZipOutputStream zos) throws IOException {
        File[] files = directory.listFiles();
        byte[] buffer = new byte[1024];
        int read = -1;
        for (File file : files) {
            if (file.isDirectory()) {
                zip(file, base, zos);
            } else {
                FileInputStream fis = new FileInputStream(file);
                ZipEntry entry = new ZipEntry(file.getPath().substring(base.getPath().length() + 1));
                zos.putNextEntry(entry);
                while ((read = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, read);
                }
                zos.closeEntry();
                fis.close();
            }
        }
    }
}