/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kis.controller

import org.bouncycastle.crypto.BufferedBlockCipher
import org.bouncycastle.crypto.CryptoException
import org.bouncycastle.crypto.engines.AESEngine
import org.bouncycastle.crypto.modes.CBCBlockCipher
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher
import org.bouncycastle.crypto.params.KeyParameter
import org.bouncycastle.util.encoders.Base64


/**
 *
 * @author sazevedo
 */
class Criptography {
	
    static String encode(byte[] plain, String pwd) throws CryptoException {
        
        def ngn = new AESEngine()
        
        BufferedBlockCipher encoder = new PaddedBufferedBlockCipher(new CBCBlockCipher(ngn))
        
        pwd = pwd.length() > 24? pwd[0..23]: pwd
        
        def key = String.format("%-24s", pwd).bytes
        
        encoder.init(true, new KeyParameter(key))
        
        def cipher = new byte[encoder.getOutputSize(plain.length)]
        
        int len = encoder.processBytes(plain, 0, plain.length, cipher, 0)
    
        encoder.doFinal(cipher, len)
    
        return new String(Base64.encode(cipher))    
    }
    
    static byte[] decode(String cipher, String pwd) throws CryptoException {
     
        if(!cipher) {
            return
        }
        
        def ngn = new AESEngine()
        
        BufferedBlockCipher decoder = new PaddedBufferedBlockCipher(new CBCBlockCipher(ngn))
    
        pwd = pwd.length() > 24? pwd[0..23]: pwd
        
        def key = String.format("%-24s", pwd).bytes
        
        byte[] clearCipher = Base64.decode(cipher)

        decoder.init(false, new KeyParameter(key))

        def plain = new byte[decoder.getOutputSize(clearCipher.length)]

        int len = decoder.processBytes(clearCipher, 0, clearCipher.length, plain, 0)

        len += decoder.doFinal(plain, len)

        return plain
        
    }
}

