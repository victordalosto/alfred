package alfred.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import alfred.models.AlfredBackpack;


public class UnzipDirectory {

    private static final String ALGORITHM = "AES";

    
    public static void executa(String chave) {
        byte [] KEY = Hash.generateHash(chave);
        AlfredBackpack.getDestinyDirectories().forEach(dir -> {
            try {
                String output = DirectoryService.getValidName(dir);
                unzipDirectory(new File(dir + ".zip"), new File(output), KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private static void unzipDirectory(File zipFile, File outputDirectory, byte [] KEY) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        try (ZipInputStream zis = new ZipInputStream(new CipherInputStream(
                new FileInputStream(zipFile), cipher))) {
            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {
                File entryFile = new File(outputDirectory, entry.getName());
                if (!entry.isDirectory()) {
                    entryFile.getParentFile().mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(entryFile)) {
                        byte[] buffer = new byte[1024];
                        int read = -1;
                        while ((read = zis.read(buffer)) != -1) {
                            fos.write(buffer, 0, read);
                        }
                    }
                }
            }
            System.out.println("Arquivo descompactado: " + outputDirectory);
        }
    }
}
