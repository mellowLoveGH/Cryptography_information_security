

import java.math.BigInteger;

public class RSA {

	public static void main(String[] args) {

		RSA rsa = new RSA();
		
		//
		long p = 17;
		long q = 31;
		BigInteger m = new BigInteger("5");
		BigInteger n = rsa.getN(p, q);
		long e = rsa.pubKey(p, q);
		long d = rsa.priKey(p, q, e);
		BigInteger c = rsa.encode(m, e, n);
		m = rsa.decode(c, d, n);
		System.out.println("e: " + e + ", d: " + d);
		System.out.println("m: " + m + ", c: " + c);
	}
	
	public BigInteger getN(long p, long q) {
		BigInteger n = new BigInteger(p * q + "");
		return n;
	}
	
	public long pubKey(long p, long q) {
		long fn = (p - 1) * (q - 1);
		long e = 2;
		
		while(fn % e == 0) {
			e++;			
		}
		
		return e;
	}
	
	
	public long priKey(long p, long q, long e) {
		long fn = (p - 1) * (q - 1);
		long d = 1;
		long flag = (e * d) % fn;
		while(flag != 1) {
			d++;
			flag = (e * d) % fn;
		}
		
		return d;
	}
	
	// encode
	public BigInteger encode(BigInteger m, long e, BigInteger n) {
		BigInteger c = power(m, e).mod(n);
		return c;
	}

	//
	public BigInteger decode(BigInteger c, long d, BigInteger n) {
		BigInteger m = power(c, d).mod(n);
		return m;
	}

	//
	public static BigInteger power(BigInteger base, long pow) {
		BigInteger p = new BigInteger("1");
		for (int i = 0; i < pow; i++) {
			p = p.multiply(base);
		}

		return p;
	}

}
