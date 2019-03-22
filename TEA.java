
package cs308_lab4;
public class TEA {
   
   
    private final int[] key;
    static final int DELTA = 0x9e3779b9;
   
    /* CONSTRUCTOR (initializes key with a predefined array) */
   
    public TEA(int[] key) {
        this.key = key;
    }
   
    /* CONSTRUCTOR (uses "pack_key()" to initialize key with a password) */
   
    public TEA(String key) {
        this.key = new int[4];
        pack_key(key);
    }
   
    public int[] getKey() { return key; }
   
    /* Pack characters from password into key */
   
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
   
    /* Pack characters from message string into TEA array */
   
    public int[] pack_message(String m) {
       
        int[] v = new int[2];
       
        
        /* INSERT YOUR CODE HERE */
       v[0] = 0x20202020;
       v[1] = 0x20202020;
       for (int i = 0; i < m.length(); i++) {
           char c = m.charAt(i);
           int index = (i % 2);
           v[index] = v[index] << 8;
           v[index] = v[index] | (c & 0x00FF);
       }
       
        return v;
       
    }
   
    /* Unpack characters from TEA array into message string */
   
    public String unpack_message(int[] v) {
       
        String message = "";
       
        /* INSERT YOUR CODE HERE */
        for (int i = 7; i >= 0; --i) {
            int index = (i % 2);
            char c = (char) (v[index] & (0x00FF));
            v[index] = (v[index] >>> 8);
            
            message = c + message;
            
        }
       
        return message;
       
    }
   
    /* Encrypt TEA Data */
   
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