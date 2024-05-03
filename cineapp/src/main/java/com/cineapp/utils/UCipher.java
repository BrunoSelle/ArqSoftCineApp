package com.cineapp.utils;

import java.util.Random;

/**
 *
 * @author Giuliano
 */
public class UCipher {
    
    public static final String CHAVE = "QWESRXKICzjfhypakenw";
    private static final String codes64 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+/";

    public static String makeRNDString(String chars, int count) {
        String res = "";
        for(int i = 0; i <= (count - 1); i++) {
            Random rg = new Random();
            int x = rg.nextInt(chars.length());
            res = res + chars.charAt(x);
            chars = chars.substring(0,x+1) + chars.substring(x + 1,chars.length());
        }
        return res;
    }
    
    public static String cifra(String data, String securityString) throws Exception {
        String res = "";
        int minV = 5;
        int maxV = 8;
        
        if(securityString.length()<16){
            throw new Exception("Chave deve conter no m�nimo 16 caracteres!");
        }
        String s1 = "";
        for(int i=0; i<securityString.length();i++){
          s1 = securityString.substring(i+1, securityString.length());
          if(s1.contains(securityString.substring(i, i+1))) {
              throw new Exception("Caractere se repete na chave!");
          }
          if(!codes64.contains(securityString.substring(i, i+1))) {
              throw new Exception("Caractere n�o existe na base!");
          }
        }
        
        s1 = codes64;
        String s2 = "";
        
        for(int i=0; i<securityString.length();i++){
            int x = s1.indexOf(securityString.substring(i, i+1));
            if(x>=0){
                s1 = s1.substring(0, x) + s1.substring(x+1, s1.length());
            }
        }
        String ss = securityString;
        
        for(int i=0; i<data.length();i++) {
            int posicao = (int) data.charAt(i);
            posicao = posicao % 16;
            s2 = s2 + ss.charAt(posicao);
            ss = ss.substring(ss.length()-1, ss.length()) + ss.substring(0, ss.length()-1);
            posicao = ((int) data.charAt(i)) / 16;
            s2 = s2 + ss.charAt(posicao);
            ss = ss.substring(ss.length()-1, ss.length()) + ss.substring(0, ss.length()-1);
        }
        Random rg = new Random();
        res = makeRNDString(s1, rg.nextInt(maxV - minV) + minV + 1);
        for(int i=0; i<s2.length();i++){
            res = res + s2.charAt(i) + makeRNDString(s1, rg.nextInt(maxV - minV) + minV);
        }

        return res;
    }
    
    public static String decifra(String data, String securityString) throws Exception {
        String s2 = "";

        if(securityString.length()<16){
            throw new Exception("Chave deve conter no m�nimo 16 caracteres!");
        }
        String s1 = "";
        for(int i=0; i<securityString.length();i++){
          s1 = securityString.substring(i+1, securityString.length());
          if(s1.contains(securityString.substring(i, i+1))) {
              throw new Exception("Caractere se repete na chave!");
          }
          if(!codes64.contains(securityString.substring(i, i+1))) {
              throw new Exception("Caractere n�o existe na base!");
          }
        }
        
        s1 = codes64;
        String ss = securityString;
        
        for(int i=0; i<data.length();i++){
            if(ss.indexOf(data.charAt(i))>=0) {
                s2 = s2 + data.charAt(i);
            } 
        }
        data = s2;
        s2 = "";
        
        if(data.length() % 2 != 0) {
              throw new Exception("Tamanho inv�lido do texto para a chave passada!");
        }
        
        for(int i=0;i<(data.length()/2);i++) {
            int x = ss.indexOf(data.charAt(i * 2));
            if(x<0){
                throw new Exception("Erro descriptografando!");
            }
            ss = ss.substring(ss.length()-1, ss.length()) + ss.substring(0, ss.length()-1);
            int x2 = ss.indexOf(data.charAt(i * 2 + 1));
            if(x2<0){
                throw new Exception("Erro descriptografando!");
            }
            x = x+x2*16;
            s2 = s2 + (char) x;
            ss = ss.substring(ss.length()-1, ss.length()) + ss.substring(0, ss.length()-1);
        }

        return s2;
    }
    
}
