package app.managers;

import java.io.File;
import java.io.FileInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class SignManager {
    /**
     * Signs the data with the private key
     *
     * @param data data to sign
     *
     * @param priv the private key used to sign the data
     * @return the signature corresponding to the data
     */
    public static byte[] signData(File file, PrivateKey priv) {
        byte[] signature = null; // The signature

        try {
            FileInputStream fis = new FileInputStream(file);
            Signature signer = Signature.getInstance("SHA1withRSA"); // Gets an instance of a Signature (our signer
                                                                     // object), prepared to sign using SHA1withRSA

            signer.initSign(priv); // Initialize the signer with the private key

            byte[] buffer = new byte[512];
            int readed = 0;

            while ((readed = fis.read(buffer)) != -1) {
                signer.update(buffer, 0, readed); // Signs the data
            }

            signature = signer.sign(); // Finish the signature (add last block, which includes the padding) and obtain
                                       // it
            fis.close();
        } catch (Exception ex) {
            System.err.println("Error signing data: " + ex);
        }
        return signature; // Returns the signature
    }

    /**
     * Verifies if a signature correspond to the data and the public key supposedly
     * related
     *
     * @param data      the data
     * @param signature the signature of the data
     * @param pub       the public key, used to verify if the signature is related
     *                  to the data using it
     * @return true if the signature was obtained signin the data with the private
     *         key corresponding to the public one false otherwise
     */
    public static boolean validateSignature(File file, byte[] signature, PublicKey pub) {
        boolean isValid = false; // Up to now, the signature is invalid
        try {
            FileInputStream fis = new FileInputStream(file);
            Signature signer = Signature.getInstance("SHA1withRSA"); // Gets an instance of a Signature (our signer
            // object), prepared to verify using SHA1withRSA
            signer.initVerify(pub); // Initialize the signer with the public key

            byte[] buffer = new byte[512];
            int readed = 0;

            while ((readed = fis.read(buffer)) != -1) {
                signer.update(buffer, 0, readed); // Verify the data
            }

            isValid = signer.verify(signature); // Finish the verification and returns the result

            fis.close();
        } catch (Exception ex) {
            System.err.println("Error validating data: " + ex);
        }

        return isValid; // Returns the result of the sign validation
    }
}
