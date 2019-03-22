# TEA-Algorithm
## Overview
This program is an implementation of the Tiny Encryption Algorithm. The "TEA" algorithm was created by David Wheeler and Roger Needham of the Cambridge Computer Laboratory. It is a simple block cipher that is noted for its simplicity of implementation. This specific implementation takes a message, in this case "UNICORNS", and takes the numerical code value of the characters and encrypts the message, and then it decrypts it. This program only consists of two classes:

- The main class, CS308_Lab4.java
- TEA.java

### CS308_Lab4.java
This class starts out with a key for the encryption and decryption, a String called **key**. This String is initialized to "700PelhamRdNorth". I then created an int Array called **teadata**. Next, the String **message** "UNICORNS" was created. The **TEA** object **tea**, from the **TEA** class, is created using the key **key**. A for loop is used print out the four tea keys, starting from 0 to 3. The program next prints out the original message "UNICORNS" to the screen. The next lines of code packs the message into **teadata** and prints them to the screen:
```

teadata = tea.pack_message(message);
System.out.println("\n2) Packed TEA Data (Word 0): " + teadata[0]);
System.out.println("3) Packed TEA Data (Word 1): " + teadata[1]);
```
Next the **tea** object encrypts the **teadata** and prints out the encrypted **teadata** Arrays. It then decrypts both of the **teadata** Arrays back into the packed TEA data and prints that to the screen. Lastly, the message is unpacked into its original form and printed to the console as the String **plaintext**: 

```

String plaintext = tea.unpack_message(teadata);
System.out.println("\n8) Unpacked TEA Plaintext: " + plaintext + " (should match #1)\n");
```
### TEA.java
This is the class that creates the **TEA** encryption and decryption object. It has two fields; an int Array **key** and an int constant **DELTA**. **DELTA** is initialized as the magic constant of the algorithm: 0x9e3779b9. The constructor intialies the **TEA** object's key. It has another constructor that initializes the key with a password using **pack_key**. The next method returns the key. The next **pack_key** method fills the key with empty spaces then packs the key with the password:
```

private void pack_key(String password) {
   
        key[0] = 0x20202020; // Pre-fill key with empty spaces
        key[1] = 0x20202020;
        key[2] = 0x20202020;
        key[3] = 0x20202020;
       
        /* INSERT YOUR CODE HERE */
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            int index = (i % 4);
            key[index] = key[index] << 8;
            key[index] = key[index] | (c & 0x00FF);
        }

    }
```
The next block of code is very similar in that it initializes two arrays with blank spaces then packs the message into them. It returns the array. The next method reverses the process and upacks the message. The final two methods are the TEA encryption and decryption methods: 
```

 public void encrypt(int[] v) {

  int v0 = v[0], v1 = v[1], sum = 0, i;
	int k0 = key[0], k1 = key[1], k2 = key[2], k3 = key[3];
	for (i = 0; i < 32; i++) {
		sum+=DELTA;
		v0 += ((v1<<4) + k0) ^ (v1 + sum) ^ ((v1 >>> 5) + k1);
		v1 += ((v0<<4) + k2) ^ (v0 + sum) ^ ((v0 >>> 5) + k3);

	}
	v[0] = v0;
	v[1] = v1;   
       
    }
   
    /* Decrypt TEA Data */
   
    public void decrypt(int[] v) {
       
     int v0=v[0], v1=v[1], sum=0xC6EF3720, i;  
                            
        int k0=key[0], k1=key[1], k2=key[2], k3=key[3];   
        for (i=0; i<32; i++) {                         
            v1 -= ((v0<<4) + k2) ^ (v0 + sum) ^ ((v0>>>5) + k3);
            v0 -= ((v1<<4) + k0) ^ (v1 + sum) ^ ((v1>>>5) + k1);
            sum -= DELTA;                                   
        }                                            
        v[0]=v0; 
	v[1]=v1;   

    }
   
}
```


