package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe que auxilia a leitura de dados do usuario. Esta classe nao deve ser
 * modificada. Para utiliza-la, crie um atributo do tipo Console na sua 
 * aplicacao.
 * 
 * Esta classe foi baseada na classe Console desenvolvido por Ives Jose de 
 * Albuquerque Macedo Junior (ijamj@cin.ufpe.br)
 */
public class Console {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Esta linha foi alterada para evitar a sobrecarga
    /**
     * Lê atributo do tipo String.
     * @return String.
     * @throws IOException 
     */
    public static String readString() throws IOException {
        while (!Console.br.ready()) {
        }
        String ret = Console.br.readLine();
        return ret==null ? "" : ret;
    }
    /**
     * Lê atributo do tipo char.
     * @return char.
     * @throws IOException 
     */
    public static char readChar() throws IOException {  // este metodo foi alterado para ficar mais robusto        
        String str = Console.readString();
        return str.length()>0 ? str.charAt(0) : '\n';
    }
    
    /**
     * Lê atributo do tipo Array de char.
     * @return arrayChar.
     * @throws IOException 
     */
    public static char[] readCharArray() throws IOException{        
        return Console.readString().toCharArray();
    }
    
    /**
     * Lê atributo do tipo short.
     * @return short.
     * @throws IOException
     * @throws NumberFormatException 
     */
    public static short readShort() throws IOException, NumberFormatException{        
        return Short.parseShort(Console.readString()); 
    }

    /**
     * Lê atributo do tipo int.
     * @return int.
     * @throws IOException
     * @throws NumberFormatException 
     */
    public static int readInt() throws IOException, NumberFormatException{
        return Integer.parseInt(Console.readString());        
    }
    
    /**
     * Lê atributo do tipo long.
     * @return long.
     * @throws IOException 
     */
    public static long readLong() throws IOException {
       return Long.parseLong(Console.readString());        
    }
    
    /**
     * Lê atributo do tipo float.
     * @return float.
     * @throws IOException 
     */
    public static float readFloat() throws IOException {
        return Float.parseFloat(Console.readString());        
    }
    /**
     * Lê atributo do tipo double.
     * @return double.
     * @throws IOException 
     */
    public static double readDouble() throws IOException {
        return Double.parseDouble(Console.readString());                
    }
}