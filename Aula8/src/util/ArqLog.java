package util;




import java.io.IOException;
import java.util.Calendar;



import service.UsuarioService;

public class ArqLog {
	
	//String usuario,senha;
	//Log log;
	//UsuarioService us;
	//Usuario user;
	static String arquivo = "C:/temp/ws-eclipse/Aula8/WebContent/log/ArqLogLogin.txt";
	
	
	public static void ArqLogLogin(String nome, String senha) {
		Log log = new Log();
		UsuarioService us = new UsuarioService();
		
		if(us.checkLogin(nome, senha) != null) {
			Calendar c = Calendar.getInstance();
			try {
				log.abrir(arquivo);
				log.escrever("Usuario: "+ nome +" Realizou Acessou : "+c.getTime()+"\n");
				log.fechar();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			if(us.checkUsuario(nome) != null) {
				Calendar c = Calendar.getInstance();
				try {
					log.abrir(arquivo);
					log.escrever("Usuario: "+nome+" Existe no Sistema, porém a senha está incorreta, Realizou acesso em : "+c.getTime()+"\n");
					log.fechar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			// Caso não exista informa que não existe
			} else {
				Calendar c = Calendar.getInstance();
				try {
					log.abrir(arquivo);
					log.escrever("Usuario: "+nome+" Não existe no sistema, realizou acesso em: "+c.getTime()+"\n");
					log.fechar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	
}


}