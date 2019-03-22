
package cs308_lab4;

public class CS308_Lab4 {

    public static void main(String[] args) {
        
	/* Local Variables */

	String key = "700PelhamRdNorth";

	int[] teadata;

	String message = "UNICORNS";

	/* Initialize TEA Object */
        
        TEA tea = new TEA(key);

	/* BEGIN */

	/* Print TEA Key */

	for (int i = 0; i < tea.getKey().length; ++i) {

            System.out.println("TEA Key (Word " + i + "): " + tea.getKey()[i]);

	}

	/* Print Message */

	System.out.println("\n1) Original Message: " + message);

	/* Pack and Print TEA Data */

	teadata = tea.pack_message(message);

	System.out.println("\n2) Packed TEA Data (Word 0): " + teadata[0]);
	System.out.println("3) Packed TEA Data (Word 1): " + teadata[1]);

	/* Encrypt and Print TEA Data */

	tea.encrypt(teadata);

	System.out.println("\n4) Encrypted TEA Data (Word 0): " + teadata[0]);
	System.out.println("5) Encrypted TEA Data (Word 1): " + teadata[1]);

	/* Decrypt and Print TEA Data */

	tea.decrypt(teadata);

	System.out.println("\n6) Decrypted TEA Data (Word 0): " + teadata[0] + " (should match #2)");
	System.out.println("7) Decrypted TEA Data (Word 1): " + teadata[1] + " (should match #3)");

	/* Unpack and Print Plaintext */

	String plaintext = tea.unpack_message(teadata);

	System.out.println("\n8) Unpacked TEA Plaintext: " + plaintext + " (should match #1)\n");
       
    }
    
}